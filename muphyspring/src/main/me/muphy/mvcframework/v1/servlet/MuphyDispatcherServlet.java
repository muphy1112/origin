package me.muphy.mvcframework.v1.servlet;

import me.muphy.mvcframework.annotation.MuphyAutoWired;
import me.muphy.mvcframework.annotation.MuphyController;
import me.muphy.mvcframework.annotation.MuphyRequestMapping;
import me.muphy.mvcframework.annotation.MuphyService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * 2019/3/25
 * 莫非
 */
public class MuphyDispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String, Object> ioc = new HashMap<>();
    private Map<String, Method> handlerMapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Detial:" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //绝对路径
        String url = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+", "/");

        if(!this.handlerMapping.containsKey(url)){
            //resp.getWriter().write("404 Not Found!!");
            if(!url.contains("movie.mp4")) return;
            InputStream is = new FileInputStream(req.getSession().getServletContext().getRealPath(req.getRequestURI()));//"E:\\workspace\\muphyspring\\target\\classes\\me\\muphy\\demo\\movie.mp4");
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            resp.getOutputStream().write(bytes);
            return;
        }

        Method method = this.handlerMapping.get(url);
        //反射获取类
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        Map<String, String[]> params = req.getParameterMap();
        String[] names = req.getParameterValues("name");
        String name = "";
        //写死的
        if(names != null){
            name = names[0];
        }
        method.invoke(ioc.get(beanName), req, resp, name);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
        //3.初始化说明到的类，并且将他们放入到IOC容器中
        doInstance();
        //4.完成依赖注入
        doAutowired();
        //5.初始化HandlerMapping
        initHandlerMapping();

        System.out.println("Muphy Spring framework is init.");
        System.out.println("输入网址: http://localhost:8080/demo/query?name=muphy");
    }

    private void initHandlerMapping() {
        if(ioc.isEmpty()) return;

        for (Map.Entry<String, Object> entry:ioc.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(MuphyController.class)) continue;

            String baseUrl = "";
            if(clazz.isAnnotationPresent(MuphyRequestMapping.class)){
                MuphyRequestMapping requestMapping = clazz.getAnnotation(MuphyRequestMapping.class);
                baseUrl += requestMapping.value();
            }

            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(MuphyRequestMapping.class)) return;
                MuphyRequestMapping requestMapping = method.getAnnotation(MuphyRequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                handlerMapping.put(url, method);
                System.out.println("Mapped:" + url + "," + method);
            }


        }
    }

    //自动依赖注入
    private void doAutowired() {
        if(ioc.isEmpty()) return;
        for (Map.Entry<String,Object> entry : ioc.entrySet()){
            //包括所有的修饰符 private public。。。
            Field[] fields= entry.getValue().getClass().getDeclaredFields();
            for(Field field:fields){
                if(field.isAnnotationPresent(MuphyAutoWired.class)){
                    MuphyAutoWired autoWired = field.getAnnotation(MuphyAutoWired.class);
                    //如果没有类型自定义，默认就是类型注入
                    String beanName = autoWired.value().trim();
                    if("".equals(beanName)){
                        //标记为AutoWired的字段肯定会在ioc中，接口也是
                        beanName = field.getType().getName();
                    }
                    //私有字段暴力访问
                    field.setAccessible(true);

                    try {
                        //用反射动态给字段赋值
                        field.set(entry.getValue(), ioc.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void doInstance() {
        if(classNames.isEmpty()) return;
        try {
            for (String className: classNames){
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(MuphyController.class)){
                    Object instance = clazz.newInstance();
                    //spring 默认消息
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                }else if(clazz.isAnnotationPresent(MuphyService.class)){
                    //自定义beanName
                    MuphyService service = clazz.getAnnotation(MuphyService.class);
                    String beanName = service.value();
                    //2.转小写
                    if("".equals(beanName)){
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    //3遍历接口自动赋值，投机取巧
                    for(Class<?> i: clazz.getInterfaces()){
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("The " + i.getName() + " is exist!!");
                        }
                        ioc.put(i.getName(),instance);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String toLowerFirstCase(String canonicalName) {
        char[] chars = canonicalName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for(File file:classPath.listFiles()){
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            } else{
                if(!file.getName().endsWith(".class")){continue;}
                String className = scanPackage + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        //找到主类下的配置文件
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

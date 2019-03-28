package me.muphy.mvcframework.v2.servlet;

import me.muphy.mvcframework.anotation.MuphyAutoWired;
import me.muphy.mvcframework.anotation.MuphyController;
import me.muphy.mvcframework.anotation.MuphyRequestMapping;
import me.muphy.mvcframework.anotation.MuphyService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * 2019/3/27
 * 莫非
 */
public class MuphyDispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String, Object> ioc = new HashMap<>();
    private Map<String, Method> handlerMapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        System.out.println("Muphy spring mvc 2.0 init.");
    }

    private void initHandlerMapping() {
        if (classNames.isEmpty()) return;
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if (!clazz.isAnnotationPresent(MuphyController.class)) continue;
                String baseUrl = "";
                if(clazz.isAnnotationPresent(MuphyRequestMapping.class)){
                    baseUrl = clazz.getAnnotation(MuphyRequestMapping.class).value();
                }
                for (Method method : clazz.getMethods()) {
                    if (method.isAnnotationPresent(MuphyRequestMapping.class)) {
                        String url = ("/" + baseUrl + "/" + method.getAnnotation(MuphyRequestMapping.class));
                        handlerMapping.put(url.replaceAll("\\/+", "/"), method);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void doAutowired() {
        if (ioc.isEmpty()) return;
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //包括所有的修饰符 private public。。。
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(MuphyAutoWired.class)) {
                    MuphyAutoWired autoWired = field.getAnnotation(MuphyAutoWired.class);
                    //如果没有类型自定义，默认就是类型注入
                    String beanName = autoWired.value().trim();
                    if ("".equals(beanName)) {
                        //标记为AutoWired的字段肯定会在ioc中，接口也是
                        //为什么需要getType
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
        if (classNames.isEmpty()) return;
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MuphyController.class)) {
                    Object instance = clazz.newInstance();
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                } else if (clazz.isAnnotationPresent(MuphyService.class)) {
                    MuphyService service = clazz.getAnnotation(MuphyService.class);
                    Object instance = clazz.newInstance();
                    //自定义名字的处理
                    String beanName = service.value().trim();
                    if ("".equals(beanName)) {
                        //以类名当Key
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    ioc.put(beanName, instance);
                    for (Class<?> clazzInterface : clazz.getInterfaces()) {
                        if (ioc.containsKey(clazzInterface)) break;
                        //为什么需要用全名
                        ioc.put(clazzInterface.getName(), instance);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replace("\\.", "/"));
        //TODU 验证URL
        System.out.println(url.getPath() + "    " + url.getFile() + "   " + url.getUserInfo() + "  " + url.toString());

        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            //验证file
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) continue;
                //String className = toLowerFirstCase(file.getName().replace(".class", ""));
                String className = scanPackage + "." + file.getName();
                classNames.add(className);
            }
        }

    }

    private String toLowerFirstCase(String replace) {
        return replace.substring(0, 1).toLowerCase() + replace.substring(1);
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

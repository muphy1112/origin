package me.muphy.mvcframework.v2.servlet;

import me.muphy.mvcframework.anotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
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
    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Server inner Error:" + e.getMessage());
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String contextPath = req.getContextPath();
        String url = req.getRequestURI().replace(contextPath, "").replaceAll("/+", "/");
        HandlerMapping handlerMapping = null;
        for (HandlerMapping mapping : handlerMappings) {
            if (mapping.getUrl().equals(url)) {
                handlerMapping = mapping;
                break;
            }
        }


        if (handlerMapping == null) {
            resp.getWriter().write("404 Not Found!!");
            return;
        }

        Class<?>[] paramTypes = handlerMapping.getParamTypes();
        Object[] paramValues = new Object[paramTypes.length];
        Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            if (!handlerMapping.getParamIndexMapping().containsKey(param.getKey())) continue;
            String value = Arrays.toString(param.getValue())
                    .replaceAll("\\[|\\]", "")
                    .replaceAll("\\s", ",");
            System.out.println(Arrays.toString(param.getValue()));
            int index = handlerMapping.getParamIndexMapping().get(param.getKey());
            paramValues[index] = convert(paramTypes[index], value);
        }

        if (handlerMapping.getParamIndexMapping().containsKey(HttpServletRequest.class.getName())) {
            int reqIdx = handlerMapping.getParamIndexMapping().get(HttpServletRequest.class.getName());
            paramValues[reqIdx] = req;
        }
        if (handlerMapping.getParamIndexMapping().containsKey(HttpServletResponse.class.getName())) {
            int respIdx = handlerMapping.getParamIndexMapping().get(HttpServletResponse.class.getName());
            paramValues[respIdx] = resp;
        }

        handlerMapping.getMethod().invoke(handlerMapping.getController(), paramValues);
    }

    private Object convert(Class<?> paramType, String value) {
        if (Integer.class == paramType || int.class == paramType) {
            return Integer.valueOf(value);
        }

        return value;
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
        if (ioc.isEmpty()) return;
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MuphyController.class)) continue;
            String baseUrl = "";
            if (clazz.isAnnotationPresent(MuphyRequestMapping.class)) {
                baseUrl = clazz.getAnnotation(MuphyRequestMapping.class).value();
            }
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(MuphyRequestMapping.class)) {
                    MuphyRequestMapping requestMapping = method.getAnnotation(MuphyRequestMapping.class);
                    String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("\\/+", "/");
                    //handlerMappings.put(url.replaceAll("\\/+", "/"), method);
                    handlerMappings.add(new HandlerMapping(url, entry.getValue(), method));
                    System.out.println("Mapred url:" + url.replaceAll("/+", "/"));
                }
            }
        }

    }

    private void doAutowired() {
        if (ioc.isEmpty()) return;
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //包括所有的修饰符 private public。。。
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(MuphyAutowired.class)) {
                    MuphyAutowired autoWired = field.getAnnotation(MuphyAutowired.class);
                    //如果没有类型自定义，默认就是类型注入
                    String beanName = autoWired.value().trim();
                    if ("".equals(beanName)) {
                        //标记为AutoWired的字段肯定会在ioc中，接口也是
                        //为什么需要getType
                        beanName = field.getType().getSimpleName();
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
                        if (ioc.containsKey(clazzInterface.getSimpleName())) break;
                        //为什么需要用全名
                        ioc.put(clazzInterface.getSimpleName(), instance);
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
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        // 验证URL
        System.out.println(url.getPath() + "    " + url.getFile() + "   " + url.getUserInfo() + "  " + url.toString());

        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            //验证file
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) continue;
                //String className = toLowerFirstCase(file.getName().replace(".class", ""));
                String className = scanPackage + "." + file.getName().replace(".class", "");
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

    public class HandlerMapping {
        private String url;
        private Method method;
        private Object controller;
        private Map<String, Integer> paramIndexMapping;

        public Class<?>[] getParamTypes() {
            return paramTypes;
        }

        public void setParamTypes(Class<?>[] paramTypes) {
            this.paramTypes = paramTypes;
        }

        private Class<?>[] paramTypes;

        public HandlerMapping(String url, Object controller, Method method) {
            this.url = url;
            this.method = method;
            this.controller = controller;
            this.paramTypes = method.getParameterTypes();
            this.paramIndexMapping = new HashMap<>();
            putParamIndexMapping(method);
        }

        private void putParamIndexMapping(Method method) {
            Annotation[][] params = method.getParameterAnnotations();
            for (int i = 0; i < params.length; i++) {
                for (Annotation param : params[i]) {
                    if (param instanceof MuphyRequestParam) {
                        String paramName = ((MuphyRequestParam) param).value();
                        if ("".equals(paramName.trim())) continue;
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
            Class<?>[] paramTypes = this.paramTypes;
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> clazz = paramTypes[i];
                if (clazz == HttpServletRequest.class || clazz == HttpServletResponse.class) {
                    paramIndexMapping.put(clazz.getName(), i);
                }
            }
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }

        public Map<String, Integer> getParamIndexMapping() {
            return paramIndexMapping;
        }

        public void setParamIndexMapping(Map<String, Integer> paramIndexMapping) {
            this.paramIndexMapping = paramIndexMapping;
        }
    }
}

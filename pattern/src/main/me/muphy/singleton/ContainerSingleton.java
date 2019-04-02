package me.muphy.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2019/4/1
 * 莫非
 */
public class ContainerSingleton {
    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getBean(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                try {
                    Object obj = Class.forName(className).newInstance();
                    ioc.put(className, obj);
                    return obj;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

        return ioc.get(className);
    }
}

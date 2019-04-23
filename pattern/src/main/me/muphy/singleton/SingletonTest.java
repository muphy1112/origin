package me.muphy.singleton;

import me.muphy.singleton.impl.LazySingleton;

import java.lang.reflect.Constructor;

/**
 * 2019/4/1
 * 莫非
 */
public class SingletonTest {

    public static void main(String[] args) throws Exception {
//        Thread t1 = new Thread(new me.muphy.util.ExectorThread());
//        Thread t2 = new Thread(new me.muphy.util.ExectorThread());
//        t1.start();
//        t2.start();

        LazySingleton lazySingleton = LazySingleton.getInstance();
        Class<?> clazz = LazySingleton.class;
        Constructor constructor = clazz.getDeclaredConstructor(Void.class);
        constructor.setAccessible(true);

        LazySingleton lazySingleton1 = (LazySingleton) constructor.newInstance();
        System.out.println(lazySingleton == lazySingleton1);

        System.out.println("end...");
    }
}

package me.muphy.util;

import me.muphy.singleton.impl.ContainerSingleton;
import me.muphy.singleton.impl.SingletonBean;

/**
 * 2019/4/1
 * 莫非
 */
public class ExectorThread implements Runnable {
    @Override
    public void run() {
//        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
//
//        System.out.println(hungrySingleton.getClass().getSimpleName() + ":" + hungrySingleton);

        SingletonBean singleton = (SingletonBean) ContainerSingleton.getBean("me.muphy.singleton.impl.SingletonBean");
        System.out.println(singleton);
    }
}

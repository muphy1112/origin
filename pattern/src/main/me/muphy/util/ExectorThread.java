package me.muphy.util;

import me.muphy.singleton.ContainerSingleton;
import me.muphy.singleton.HungrySingleton;
import me.muphy.singleton.SingletonBean;

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

        SingletonBean singleton = (SingletonBean) ContainerSingleton.getBean("me.muphy.singleton.SingletonBean");
        System.out.println(singleton);
    }
}

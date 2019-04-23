package me.muphy.singleton;

import me.muphy.util.ConcurrentExecuter;

/**
 * 2019/4/1
 * 莫非
 */
public class ContainerSingletonTest {

    public static void main(String[] args) {
//        SingletonBean singleton = (SingletonBean) ContainerSingleton.getBean("me.muphy.singleton.impl.SingletonBean");
//        System.out.println(singleton);

        ConcurrentExecuter.execte(10);

    }
}

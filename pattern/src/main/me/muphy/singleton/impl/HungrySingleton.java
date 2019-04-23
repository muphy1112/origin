package me.muphy.singleton.impl;

import java.io.Serializable;

/**
 * 2019/4/1
 * 莫非
 */
public class HungrySingleton implements Serializable {

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }

    private Object readResolve() {
        return hungrySingleton;
    }

}

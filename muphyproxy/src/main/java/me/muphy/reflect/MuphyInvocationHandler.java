package me.muphy.reflect;

import java.lang.reflect.Method;

/**
 * 2019/4/12
 * 莫非
 */
public interface MuphyInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;

}

package me.muphy.proxy;


import me.muphy.reflect.MuphyClassLoader;
import me.muphy.reflect.MuphyInvocationHandler;
import me.muphy.reflect.MuphyProxy;

import java.lang.reflect.Method;

/**
 * 2019/4/12
 * 莫非
 */
public class MuphyHeadhunting implements MuphyInvocationHandler {

    private Object programmer;

    public Object getInstance(Object programmer) throws Exception{
        this.programmer = programmer;
        Class<?> clazz = programmer.getClass();
        return MuphyProxy.newProxyInstance(new MuphyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(this.programmer, args);
        after();
        return object;
    }

    private void before(){
        System.out.println("我是猎头，开始找工作~");
    }

    private void after(){
        System.out.println("工作找到，准备签合同~");
    }
}

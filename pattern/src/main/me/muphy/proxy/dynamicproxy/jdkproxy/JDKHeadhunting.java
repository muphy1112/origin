package me.muphy.proxy.dynamicproxy.jdkproxy;

import me.muphy.proxy.dynamicproxy.Programmer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 2019/4/12
 * 莫非
 */
public class JDKHeadhunting implements InvocationHandler {

    private Programmer programmer;

    public Object getinstance(Programmer programmer) throws Exception{
        this.programmer = programmer;
        Class<?> clazz = programmer.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
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

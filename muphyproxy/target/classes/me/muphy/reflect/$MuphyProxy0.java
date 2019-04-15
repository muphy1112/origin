package me.muphy.reflect;

import me.muphy.reflect.MuphyClassLoader;
import me.muphy.reflect.MuphyInvocationHandler;
import me.muphy.reflect.MuphyProxy;

import java.lang.reflect.Method;

/**
 * 2019/4/12
 * 莫非
 */
public class $MuphyProxy0  implements me.muphy.proxy.Programmer{
private MuphyInvocationHandler h;
public $MuphyProxy0(MuphyInvocationHandler h){
 this.h = h;
}
public final void findJob(){
        try {
             Method m = me.muphy.proxy.Programmer.class.getMethod("findJob", new Class[]{});
             this.h.invoke(this, m, null);
        }catch (Throwable e){
            e.printStackTrace();
        }
}
}

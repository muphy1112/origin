package proxy;

import me.muphy.proxy.dynamicproxy.JavaTextProgrammer;
import me.muphy.proxy.dynamicproxy.Programmer;
import me.muphy.proxy.dynamicproxy.cglibproxy.CglibHeadhunting;
import me.muphy.proxy.dynamicproxy.jdkproxy.JDKHeadhunting;

/**
 * 2019/4/16
 * 莫非
 */
public class CglibProxyTest {
    public static void main(String[] args) throws Exception {
        Programmer programmer = (Programmer) new CglibHeadhunting().getInstance(JavaTextProgrammer.class);
        programmer.findJob();
//        programmer.eat();
    }
}

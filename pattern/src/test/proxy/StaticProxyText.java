package proxy;

import me.muphy.proxy.staticproxy.JavaTextProgrammer;
import me.muphy.proxy.staticproxy.proxy.Programmer;

/**
 * 2019/4/11
 * 莫非
 */
public class StaticProxyText {
    public static void main(String[] args) {
        //只能代理Java程序员
        Programmer programmer = new Programmer(new JavaTextProgrammer());
        programmer.findJob();
    }
}

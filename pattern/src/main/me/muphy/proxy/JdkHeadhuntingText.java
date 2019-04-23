package me.muphy.proxy;

import me.muphy.proxy.dynamicproxy.JavaTextProgrammer;
import me.muphy.proxy.dynamicproxy.Programmer;
import me.muphy.proxy.dynamicproxy.jdkproxy.JDKHeadhunting;

/**
 * 2019/4/12
 * 莫非
 */
public class JdkHeadhuntingText {
    public static void main(String[] args) throws Exception {
        Programmer programmer = (Programmer) new JDKHeadhunting().getInstance(new JavaTextProgrammer());
        programmer.findJob();
        programmer.eat();
    }
}

package me.muphy.proxy.dynamicproxy;

import me.muphy.proxy.dynamicproxy.Programmer;

/**
 * 2019/4/12
 * 莫非
 */
public class JavaTextProgrammer implements Programmer {
    public void findJob(){
        System.out.println("需要找一个工资高的JAVA测试的企业！");
    }

    @Override
    public void eat() {
        System.out.println("吃饭~");
    }
}

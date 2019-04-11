package me.muphy.proxy.staticproxy.proxy;

import me.muphy.proxy.staticproxy.IJavaProgrammer;

/**
 * 2019/4/11
 * 莫非
 */
public class Programmer {

    private IJavaProgrammer programmer;

    public Programmer(IJavaProgrammer programmer){
        this.programmer = programmer;
    }
    public void findJob(){
        System.out.println("投简历~");
        this.programmer.findJob();
        System.out.println("双方同意，开始签合同~");
    }
}


package me.muphy.adapter;

import me.muphy.adapter.loginadapter.v1.SinginForThirdService;

/**
 * 2019/4/19
 * 莫非
 */
public class SinginForThirdServiceTest {
    public static void main(String[] args) {
        SinginForThirdService service = new SinginForThirdService();
        System.out.println(service.regist("muphy", "123456"));
        System.out.println(service.login("muphy", "123456"));
        System.out.println(service.loginForQQ("muphy"));
    }
}

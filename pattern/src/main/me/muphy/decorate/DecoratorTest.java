package me.muphy.decorate;

import me.muphy.decorate.base.SiginService;
import me.muphy.decorate.upgrate.ISiginForThirdService;
import me.muphy.decorate.upgrate.SiginForThirdService;

/**
 * 2019/4/22
 * 莫非
 */
public class DecoratorTest {
    public static void main(String[] args) {
        ISiginForThirdService siginForThirdService = new SiginForThirdService(new SiginService());
        siginForThirdService = new SiginForThirdService(siginForThirdService);
        siginForThirdService.loginForQQ("username");
        siginForThirdService.login("username", "123456");
    }
}

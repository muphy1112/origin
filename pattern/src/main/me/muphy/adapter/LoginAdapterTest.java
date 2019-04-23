package me.muphy.adapter;

import me.muphy.adapter.loginadapter.v2.IPassPortForThird;
import me.muphy.adapter.loginadapter.v2.PassPortForThirdAdapter;

/**
 * 2019/4/19
 * 莫非
 */
public class LoginAdapterTest {
    public static void main(String[] args) {
        IPassPortForThird adapter = new PassPortForThirdAdapter();
        System.out.println(adapter.loginForWeChat("qq"));
//        System.out.println(adapter.loginForWeChat("wechat"));
    }
}

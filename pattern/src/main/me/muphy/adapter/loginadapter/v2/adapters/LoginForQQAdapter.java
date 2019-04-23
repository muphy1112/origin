package me.muphy.adapter.loginadapter.v2.adapters;

import me.muphy.adapter.loginadapter.MsgResult;

/**
 * 2019/4/20
 * 莫非
 */
public class LoginForQQAdapter implements LoginAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    @Override
    public MsgResult login(String id, Object adapter) {
        return new MsgResult("200", "登陆成功", "哈哈哈");
    }
}

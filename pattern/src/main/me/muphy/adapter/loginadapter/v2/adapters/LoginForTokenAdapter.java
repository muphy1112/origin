package me.muphy.adapter.loginadapter.v2.adapters;

import me.muphy.adapter.loginadapter.MsgResult;

/**
 * 2019/4/20
 * 莫非
 */
public class LoginForTokenAdapter implements LoginAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTokenAdapter;
    }

    @Override
    public MsgResult login(String id, Object adapter) {
        return null;
    }
}

package me.muphy.adapter.loginadapter.v2.adapters;

import me.muphy.adapter.loginadapter.MsgResult;

/**
 * 2019/4/20
 * 莫非
 * 接口可有可无
 */
public interface LoginAdapter {
    boolean support(Object object);
    MsgResult login(String id, Object adapter);
}

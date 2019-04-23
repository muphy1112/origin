package me.muphy.decorate.base;

import me.muphy.adapter.loginadapter.MsgResult;

/**
 * 2019/4/22
 * 莫非
 */
public interface ISiginService {
    MsgResult regist(String username, String password);
    MsgResult login(String username, String password);
}

package me.muphy.decorate.base;

import me.muphy.adapter.loginadapter.MsgResult;

/**
 * 2019/4/19
 * 莫非
 */
public class SiginService implements ISiginService {

    @Override
    public MsgResult regist(String username, String password){
        return new MsgResult("200", "注册成功", "欢迎注册阿里巴巴系统");
    }

    @Override
    public MsgResult login(String username, String password){
        return new MsgResult("200", "登陆成功", "欢迎登陆到阿里巴巴系统");
    }
}

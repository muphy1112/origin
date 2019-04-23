package me.muphy.adapter.loginadapter.v2;

import me.muphy.adapter.loginadapter.MsgResult;

/**
 * 2019/4/20
 * 莫非
 */
public interface IPassPortForThird {
    /**
     * openId 加长的用户名  全局唯一
     */
    MsgResult loginForQQ(String openId);

    MsgResult loginForWeChat(String openId);

    MsgResult loginForToken(String token);

    /**
     * code 验证码
     * */
    MsgResult loginForTel(String tel, String code);
}

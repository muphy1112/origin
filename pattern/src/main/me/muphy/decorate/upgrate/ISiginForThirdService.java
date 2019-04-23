package me.muphy.decorate.upgrate;

import me.muphy.adapter.loginadapter.MsgResult;
import me.muphy.decorate.base.ISiginService;

/**
 * 2019/4/22
 * 莫非
 */
public interface ISiginForThirdService extends ISiginService {
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

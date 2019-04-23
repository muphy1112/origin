package me.muphy.adapter.loginadapter.v1;

import me.muphy.adapter.loginadapter.MsgResult;
import me.muphy.adapter.loginadapter.service.SiginService;

/**
 * 2019/4/19
 * 莫非
 */
public class SinginForThirdService extends SiginService {
    /**
     * openId 加长的用户名  全局唯一
     */
    public MsgResult loginForQQ(String openId){
        return loginForRegidt(openId, null);
    }

    public MsgResult loginForWeChat(String openId){
        return loginForRegidt(openId, null);
    }

    public MsgResult loginForToken(String token){
        return null;
    }

    /**
     * code 验证码
     * */
    public MsgResult loginForTel(String tel, String code){
        return loginForRegidt(tel, code);
    }

    private MsgResult loginForRegidt(String username, String password) {
        super.regist(username, password);
        return super.login(username, password);
    }
}

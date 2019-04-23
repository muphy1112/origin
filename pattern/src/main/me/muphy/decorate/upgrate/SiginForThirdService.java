package me.muphy.decorate.upgrate;

import me.muphy.adapter.loginadapter.MsgResult;
import me.muphy.decorate.base.ISiginService;
import me.muphy.decorate.base.SiginService;

/**
 * 2019/4/22
 * 莫非
 */
public class SiginForThirdService implements ISiginForThirdService {

    private ISiginService siginService;

    public SiginForThirdService(ISiginService siginService) {
        this.siginService = siginService;
    }

    @Override
    public MsgResult loginForQQ(String openId) {
        return null;
    }

    @Override
    public MsgResult loginForWeChat(String openId) {
        return null;
    }

    @Override
    public MsgResult loginForToken(String token) {
        return null;
    }

    @Override
    public MsgResult loginForTel(String tel, String code) {
        return null;
    }

    @Override
    public MsgResult regist(String username, String password) {
        return this.siginService.regist(username, password);
    }

    @Override
    public MsgResult login(String username, String password) {
        return this.siginService.login(username, password);
    }
}

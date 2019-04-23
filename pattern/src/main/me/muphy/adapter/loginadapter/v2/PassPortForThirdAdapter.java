package me.muphy.adapter.loginadapter.v2;

import me.muphy.adapter.loginadapter.MsgResult;
import me.muphy.adapter.loginadapter.service.SiginService;
import me.muphy.adapter.loginadapter.v2.adapters.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 2019/4/20
 * 莫非
 */
public class PassPortForThirdAdapter extends SiginService implements IPassPortForThird {

    private final static List<LoginAdapter> adapters = new ArrayList<>();

    static {
        adapters.add(new LoginForQQAdapter());
        adapters.add(new LoginForWeChatAdapter());
    }

    @Override
    public MsgResult loginForQQ(String openId) {

        return processLogin(openId, LoginForQQAdapter.class);
    }


    @Override
    public MsgResult loginForWeChat(String openId) {
        return processLogin(openId, LoginForWeChatAdapter.class);
    }

    @Override
    public MsgResult loginForToken(String token) {
        return null;
    }

    @Override
    public MsgResult loginForTel(String tel, String code) {
        return null;
    }

    private MsgResult processLogin(String openId, Class<? extends LoginAdapter> clazz) {
        try {
            LoginAdapter adapter = clazz.newInstance();
            for (LoginAdapter loginAdapter : adapters) {
                if (loginAdapter.support(adapter)) {
                    return loginAdapter.login(openId, adapter);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return new MsgResult("500", "登陆失败", "服务器错误");
    }
}

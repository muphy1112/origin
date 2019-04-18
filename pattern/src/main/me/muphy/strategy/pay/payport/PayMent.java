package me.muphy.strategy.pay.payport;

import me.muphy.strategy.pay.MsgResult;

/**
 * 2019/4/18
 * 莫非
 */
public abstract class PayMent {
    public abstract String getName();
    protected abstract double queryBalance(String uid);

    public MsgResult pay(String uid, double amount){
        if(queryBalance(uid) < amount){
            return new MsgResult("500", "交易失败", "余额不足");
        }
        return new MsgResult("200", "交易成功", "支付金额" + amount);
    }
}

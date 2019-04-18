package me.muphy.strategy.pay.payport;

/**
 * 2019/4/18
 * 莫非
 */
public class WeChatPay extends PayMent {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 750.56;
    }
}

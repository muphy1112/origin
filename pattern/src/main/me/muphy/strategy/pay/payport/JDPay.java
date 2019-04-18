package me.muphy.strategy.pay.payport;

/**
 * 2019/4/18
 * 莫非
 */
public class JDPay extends PayMent {
    @Override
    public String getName() {
        return "京东支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 256;
    }
}

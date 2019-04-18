package me.muphy.strategy.pay.payport;

/**
 * 2019/4/18
 * 莫非
 */
public class AliPay extends PayMent{
    @Override
    public String getName() {
        return "支付宝支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 500;
    }
}

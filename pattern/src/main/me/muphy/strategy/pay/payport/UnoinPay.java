package me.muphy.strategy.pay.payport;

/**
 * 2019/4/18
 * 莫非
 */
public class UnoinPay extends PayMent{

    @Override
    public String getName() {
        return "银联支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 128;
    }
}

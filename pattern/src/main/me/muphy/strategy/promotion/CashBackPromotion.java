package me.muphy.strategy.promotion;

/**
 * 2019/4/18
 * 莫非
 */
public class CashBackPromotion implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现到支付宝");
    }
}

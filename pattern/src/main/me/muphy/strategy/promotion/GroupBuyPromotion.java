package me.muphy.strategy.promotion;

/**
 * 2019/4/18
 * 莫非
 */
public class GroupBuyPromotion implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("团购打折");
    }
}

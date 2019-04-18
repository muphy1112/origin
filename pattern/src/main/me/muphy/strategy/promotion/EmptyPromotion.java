package me.muphy.strategy.promotion;

/**
 * 2019/4/17
 * 莫非
 */
public class EmptyPromotion implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("原价");
    }
}

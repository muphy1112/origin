package me.muphy.strategy.promotion;

/**
 * 2019/4/18
 * 莫非
 */
public class CouponPromotion implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("原价减去优惠券");
    }
}

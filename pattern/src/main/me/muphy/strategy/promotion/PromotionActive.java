package me.muphy.strategy.promotion;

/**
 * 2019/4/18
 * 莫非
 */
public class PromotionActive {
    private PromotionStrategy promotionStrategy;

    public PromotionActive(PromotionStrategy promotionStrategy){
        this.promotionStrategy = promotionStrategy;
    }

    public void execute(){
        this.promotionStrategy.doPromotion();
    }
}

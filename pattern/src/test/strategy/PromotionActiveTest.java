package strategy;

import me.muphy.strategy.promotion.PromotionStrategy;
import me.muphy.strategy.promotion.PromotionStrategyFactory;

/**
 * 2019/4/18
 * 莫非
 */
public class PromotionActiveTest {

//    public static void main(String[] args) {
//        new PromotionActive(new CouponPromotion()).execute();
//        new PromotionActive(new CashBackPromotion()).execute();
//    }

//    public static void main(String[] args) {
//        PromotionStrategy promotionStrategy = null;
//        String pkey = "COUPON";
//
//        if(pkey.equals("COUPON")){
//            promotionStrategy = new CouponPromotion();
//        } else{
//            promotionStrategy = new EmptyPromotion();
//        }
//
//        promotionStrategy.doPromotion();
//    }

    public static void main(String[] args) {
        String pkey = "COUPON";
        PromotionStrategy promotionStrategy = new PromotionStrategyFactory().getPromotionStrategy(pkey);
        promotionStrategy.doPromotion();
    }
}

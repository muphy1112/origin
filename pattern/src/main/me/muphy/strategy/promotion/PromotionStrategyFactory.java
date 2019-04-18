package me.muphy.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * 2019/4/18
 * 莫非
 */
public class PromotionStrategyFactory {

    public PromotionStrategyFactory(){}

    private static Map<String, PromotionStrategy> promotionStrategys = new HashMap<>();

    static {
        promotionStrategys.put("COUPON", new CouponPromotion());
        promotionStrategys.put("CASHBACK", new CashBackPromotion());
        promotionStrategys.put("GROUPBUY", new GroupBuyPromotion());
        promotionStrategys.put("EMPTY", new EmptyPromotion());
    }

    public PromotionStrategy getPromotionStrategy(String pkey){
        return promotionStrategys.get(pkey);
    }
}

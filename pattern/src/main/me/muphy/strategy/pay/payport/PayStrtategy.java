package me.muphy.strategy.pay.payport;

import java.util.HashMap;
import java.util.Map;

/**
 * 2019/4/18
 * 莫非
 */
public class PayStrtategy {

    private static Map<String, PayMent> payStrategy = new HashMap<>();

    public final static String ALI_PAY = "AliPay";
    public final static String WECHAT_PAY = "WeChatPay";
    public final static String UNION_PAY = "UnionPay";
    public final static String JD_PAY = "JDPay";
    public final static String DEFAULT_PAY = "AliPay";

    static {
        payStrategy.put(ALI_PAY, new AliPay());
        payStrategy.put(WECHAT_PAY, new WeChatPay());
        payStrategy.put(UNION_PAY, new UnoinPay());
        payStrategy.put(JD_PAY, new JDPay());
    }

    public PayMent get(String payKey) {
        if (payStrategy.containsKey(payKey)) {
            return payStrategy.get(payKey);
        }
        return payStrategy.get(DEFAULT_PAY);
    }
}

package me.muphy.strategy.pay;

import me.muphy.strategy.pay.payport.PayMent;
import me.muphy.strategy.pay.payport.PayStrtategy;

/**
 * 2019/4/18
 * 莫非
 */
public class Order {

    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    public MsgResult pay(String payKey){
        PayMent payMent = new PayStrtategy().get(payKey);
        System.out.println("欢迎使用" + payMent.getName());
        System.out.println("本次交易金额为" + amount + ",开始扣款...");
        return payMent.pay(uid, amount);
    }
}

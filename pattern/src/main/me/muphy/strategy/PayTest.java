package me.muphy.strategy;

import me.muphy.strategy.pay.MsgResult;
import me.muphy.strategy.pay.Order;
import me.muphy.strategy.pay.payport.PayStrtategy;

/**
 * 2019/4/18
 * 莫非
 */
public class PayTest {
    public static void main(String[] args) {
        Order order = new Order("muphy", "20190418012400", 300.89);
        MsgResult msg = order.pay(PayStrtategy.JD_PAY);
        System.out.println(msg.toString());
    }
}

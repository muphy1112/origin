package proxy;

import me.muphy.proxy.staticproxy.IOrderService;
import me.muphy.proxy.staticproxy.Order;
import me.muphy.proxy.staticproxy.OrderDao;
import me.muphy.proxy.staticproxy.OrderService;
import me.muphy.proxy.staticproxy.proxy.OrderServiceStaticProxy;

import java.util.Date;

/**
 * 2019/4/12
 * 莫非
 */
public class dbRouterTest {
    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime(new Date(150121546).getTime());
        IOrderService orderService = new OrderServiceStaticProxy(new OrderService(new OrderDao()));
        orderService.createOrder(order);
    }
}

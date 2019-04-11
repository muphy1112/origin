package me.muphy.proxy.staticproxy.proxy;

import me.muphy.proxy.staticproxy.IOrderService;
import me.muphy.proxy.staticproxy.Order;
import me.muphy.proxy.staticproxy.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2019/4/11
 * 莫非
 */
public class OrderServiceStaticProxy implements IOrderService {

    private IOrderService orderService;
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    public OrderServiceStaticProxy(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public int createOrder(Order order) {
        Long time = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
        System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据~");
        DynamicDataSourceEntity.set(dbRouter);
        int c = this.orderService.createOrder(order);
        DynamicDataSourceEntity.restory();
        return c;
    }
}

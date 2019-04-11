package me.muphy.proxy.staticproxy;

/**
 * 2019/4/11
 * 莫非
 */
public class OrderService implements IOrderService {

    private OrderDao orderDao;

    public OrderService(OrderDao dao){
        this.orderDao = dao;
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService调用OrderDao创建订单~");
        return orderDao.insert(order);
    }
}

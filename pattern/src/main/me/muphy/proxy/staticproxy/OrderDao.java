package me.muphy.proxy.staticproxy;

/**
 * 2019/4/11
 * 莫非
 */
public class OrderDao {
    public int insert(Order order){
        System.out.println("OrderDao创建一条Order成功~");
        return 1;
    }
}

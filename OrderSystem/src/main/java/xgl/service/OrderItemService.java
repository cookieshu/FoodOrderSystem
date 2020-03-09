package xgl.service;

import xgl.pojo.Order;
import xgl.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {

    void add(OrderItem orderItem);
    void delete(int id);
    void update(OrderItem orderItem);

    OrderItem get(int id);
    List<OrderItem> list();

    void fill(List<Order> os);

    void fill(Order o);

    //获取销量
    int getSaleCount(int  fid);

    List<OrderItem> listByUser(int uid);
}

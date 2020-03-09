package xgl.service;

import xgl.pojo.Order;
import xgl.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    //订单的状态
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order order);
    void delete(int id);
    void update(Order order);

    Order get(int id);
    List<Order> list();

    //生成订单
    float add(Order c,List<OrderItem> ois);

    //根据用户查询订单
    List list(int uid, String excludedStatus);
}

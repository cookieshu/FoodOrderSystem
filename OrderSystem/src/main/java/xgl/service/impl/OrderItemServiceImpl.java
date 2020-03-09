package xgl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xgl.mapper.OrderItemMapper;
import xgl.pojo.Food;
import xgl.pojo.Order;
import xgl.pojo.OrderItem;
import xgl.pojo.OrderItemExample;
import xgl.service.FoodService;
import xgl.service.OrderItemService;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    FoodService foodService;

    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    //设置OrderItem的Food属性
    private void setFood(OrderItem orderItem){
        Food food = foodService.get(orderItem.getFid());
        orderItem.setFood(food);
    }
    private void setFood(List<OrderItem> orderItems){
        for (OrderItem orderItem:orderItems){
            setFood(orderItem);
        }
    }

    @Override
    public OrderItem get(int id) {
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
        //设置food属性
        setFood(orderItem);
        return orderItem;
    }

    @Override
    public List<OrderItem> list() {
        OrderItemExample example=new OrderItemExample();
        example.setOrderByClause("id ASC");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public void fill(List<Order> os) {
        for (Order order:os){
            fill(order);
        }
    }

    //把订单项填入订单
    @Override
    public void fill(Order o) {
        //1,查询出该订单的所有订单项
        OrderItemExample example =new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        List<OrderItem> ois =orderItemMapper.selectByExample(example);
        //设置Food属性
        setFood(ois);

        //2,计算oder的总金额和总订单项数量
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : ois) {
            total+=oi.getNumber()*oi.getFood().getPrice();
            totalNumber+=oi.getNumber();
        }
        //设置该订单的属性：金额，数量，订单项列表
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(ois);
    }

    @Override
    public int getSaleCount(int fid) {
        OrderItemExample example=new OrderItemExample();
        example.createCriteria().andFidEqualTo(fid);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        int result=0;
        for (OrderItem oi:orderItems){
            result+=oi.getNumber();
        }
        return result;
    }

    //立即下单
    @Override
    public List<OrderItem> listByUser(int uid) {
        OrderItemExample example=new OrderItemExample();
        example.createCriteria().andUidEqualTo(uid).andOidIsNull();
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        setFood(orderItems);
        return orderItems;
    }
}

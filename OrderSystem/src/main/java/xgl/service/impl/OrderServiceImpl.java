package xgl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xgl.mapper.OrderMapper;
import xgl.pojo.Order;
import xgl.pojo.OrderExample;
import xgl.pojo.OrderItem;
import xgl.pojo.User;
import xgl.service.OrderItemService;
import xgl.service.OrderService;
import xgl.service.UserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    //设置用户User属性
    private void setUser(Order order){
        Integer uid = order.getUid();
        User user = userService.get(uid);
        order.setUser(user);
    }

    private void setUser(List<Order> orders){
        for (Order order:orders){
            setUser(order);
        }
    }
    @Override
    public Order get(int id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        //设置user属性
        setUser(order);
        return order;
    }

    @Override
    public List<Order> list() {
        OrderExample example=new OrderExample();
        List<Order> orders = orderMapper.selectByExample(example);
        //设置user属性
        setUser(orders);
        return orders;
    }

    @Override       //通过注解进行事务管理
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")

    public float add(Order o, List<OrderItem> ois) {
        float total = 0;
        add(o);

        /*//模拟当增加订单后出现异常，观察事务管理是否预期发生。（需要把false修改为true才能观察到）
        if(false)
            throw new RuntimeException();*/

        for (OrderItem oi: ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total+=oi.getFood().getPrice()*oi.getNumber();
        }
        return total;
    }

    @Override
    public List list(int uid, String excludedStatus) {
        OrderExample example =new OrderExample();

        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }
}

package xgl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xgl.pojo.Order;
import xgl.service.OrderItemService;
import xgl.service.OrderService;
import xgl.util.Page;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/*订单的增加和删除，都是在前台进行的。 所以OrderController提供的是list方法和delivery(发货)方法*/
@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("/order_list")
    public String list(Page page, Model model){
        //设置分页
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Order> orders = orderService.list();
        int total = (int) new PageInfo<>(orders).getTotal();
        page.setTotal(total);

        //把每个order填上OrderItem
        orderItemService.fill(orders);

        model.addAttribute("orders",orders);
        model.addAttribute("page",page);
        return "admin/listOrder";
    }

    //发货方法！
    @RequestMapping("/order_delivery")
    public String delivery(Order o) throws IOException {
        //设置Order创建时间
        o.setCreateDate(new Date());
        //设置Order状态
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return "redirect:/order_list";
    }
}

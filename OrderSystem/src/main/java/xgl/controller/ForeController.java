package xgl.controller;

import com.github.pagehelper.PageHelper;
import comparator.*;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import xgl.pojo.*;
import xgl.service.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//前端控制类
@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CanteenService canteenService;
    @Autowired
    ShopService shopService;
    @Autowired
    FoodService foodService;
    @Autowired
    UserService userService;
    @Autowired
    FoodImageService foodImageService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;
    //首页
    @RequestMapping("/forehome")
    public String home(Model model){
        //1,查询所有食堂
        List<Canteen> canteens = canteenService.list();
        //2,为这些食堂填充店铺
        shopService.fill(canteens);
        //3,填充 推荐店铺集合
        shopService.fillByRow(canteens);

        //为窗口填充菜品
        for (int i=0;i<canteens.size();i++){
            //获取店铺
            List<Shop> shops = canteens.get(i).getShops();
            //填充菜品
            foodService.fill(shops);
            //填充推荐菜品
            foodService.fillByRow(shops);
        }

        model.addAttribute("canteens",canteens);
        return "fore/home";
    }

    //注册
    @RequestMapping("foreregister")
    public String register(Model model,User user) {
        String name =  user.getName();
        //通过HtmlUtils.htmlEscape(name);把账号里的特殊符号进行转义
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);

        if(exist){
            String m ="用户名已经被使用,不能使用";
            model.addAttribute("msg", m);
            model.addAttribute("user", null);
            return "fore/register";
        }
        userService.add(user);

        return "redirect:/registerSuccessPage";
    }

    //登录
    @RequestMapping("forelogin")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session) {
        //防止恶意注册
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name,password);

        if(null==user){
            model.addAttribute("msg", "账号密码错误");
            return "fore/login";
        }
        //把用户对象放到session中
        session.setAttribute("user", user);
        return "redirect:/forehome";
    }

    //退出操作，
    @RequestMapping("forelogout")
    public String logout( HttpSession session) {
        //从session中移除掉user
        session.removeAttribute("user");
        return "redirect:/forehome";
    }

    //菜品详情页
    @RequestMapping("/forefood")
    public String product( int fid, Model model) {
        Food food = foodService.get(fid);
        //获取菜品的图片
        List<FoodImage> foodSingleImages = foodImageService.list(food.getId(), FoodImageService.type_single);
        List<FoodImage> foodDetailImages = foodImageService.list(food.getId(), FoodImageService.type_detail);
        //设置图片集
        food.setFoodSingleImages(foodSingleImages);
        food.setFoodDetailImages(foodDetailImages);
        //获取food评价
        List<Review> reviews = reviewService.list(food.getId());
        //设置food的销量和评价数量
        foodService.setSaleAndReviewNumber(food);

        model.addAttribute("reviews", reviews);
        model.addAttribute("food", food);
        return "fore/food";
    }

    //检查是否登录
    @RequestMapping("/forecheckLogin")
    @ResponseBody
    public String checkLogin( HttpSession session) {
        //从session中获取user
        User user =(User)  session.getAttribute("user");
        if(null!=user)
            return "success";
        return "fail";
    }

    //拟态登录(小窗口登录)
    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password,HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name,password);

        if(null==user){
            return "fail";
        }
        session.setAttribute("user", user);
        return "success";
    }

    //展示该店铺下的food列表，有五大排序！
    @RequestMapping("/foreshop")
    public String shop(int sid,String sort, Model model) {
        //获取店铺
        Shop shop = shopService.get(sid);
        //填充菜品
        foodService.fill(shop);
        //给该店铺下的菜品设置销量和评价
        foodService.setSaleAndReviewNumber(shop.getFoods());

        if(null!=sort){
            switch(sort){
                case "review":
                    Collections.sort(shop.getFoods(),new FoodReviewComparator());
                    break;
                case "date" :
                    Collections.sort(shop.getFoods(),new FoodDateComparator());
                    break;

                case "saleCount" :
                    Collections.sort(shop.getFoods(),new FoodSaleCountComparator());
                    break;

                case "price":
                    Collections.sort(shop.getFoods(),new FoodPriceComparator());
                    break;

                case "all":
                    Collections.sort(shop.getFoods(),new FoodAllComparator());
                    break;
            }
        }

        model.addAttribute("shop", shop);
        return "fore/shop";
    }

    //搜索
    @RequestMapping("foresearch")
    public String search( String keyword,Model model){

        PageHelper.offsetPage(0,20);
        List<Food> foods= foodService.search(keyword);
        foodService.setSaleAndReviewNumber(foods);
        model.addAttribute("foods",foods);
        return "fore/searchResult";
    }

    //立即下单
    @RequestMapping("forebuyone")
    public String buyone(int fid, int num, HttpSession session) {
        Food food = foodService.get(fid);
        int oiid = 0;
        //获取user
        User user =(User)  session.getAttribute("user");

        boolean found = false;
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            //我的菜谱中有同样的菜品
            if(oi.getFood().getId().intValue()==food.getId().intValue()){
                //更改数量
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                found = true;
                //获取订单项id
                oiid = oi.getId();
                break;
            }
        }
        //我的菜谱中没有同样的菜品
        if(!found){
            OrderItem oi = new OrderItem();
            oi.setUid(user.getId());
            oi.setNumber(num);
            oi.setFid(fid);
            orderItemService.add(oi);
            oiid = oi.getId();
        }
        return "redirect:forebuy?oiid="+oiid;
    }

    //结算
    @RequestMapping("forebuy")
    public String buy( Model model,String[] oiid,HttpSession session){
        List<OrderItem> ois = new ArrayList<>();
        float total = 0;

        //遍历每一个的订单项。
        for (String strid : oiid) {
            int id = Integer.parseInt(strid);
            OrderItem oi= orderItemService.get(id);
            //计算总价
            total +=oi.getFood().getPrice()*oi.getNumber();
            ois.add(oi);
        }

        session.setAttribute("ois", ois);
        model.addAttribute("total", total);
        return "fore/buy";
    }

    //加入我的菜单
    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(int fid, int num,HttpSession session) {
        Food food = foodService.get(fid);
        //取出user
        User user =(User)  session.getAttribute("user");
        boolean found = false;
        //获取所有的订单项
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if(oi.getFood().getId().intValue()==food.getId().intValue()){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                found = true;
                break;
            }
        }

        if(!found){
            OrderItem oi = new OrderItem();
            oi.setUid(user.getId());
            oi.setNumber(num);
            oi.setFid(fid);
            orderItemService.add(oi);
            System.out.println("加入菜单");
        }
        return "success";
    }

    //查看 我的菜单
    @RequestMapping("forecart")
    public String cart( Model model,HttpSession session) {
        User user =(User)  session.getAttribute("user");
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        model.addAttribute("ois", ois);
        return "fore/cart";
    }

    //调整订单项菜品的数量
    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem( HttpSession session, int fid, int number) {
        User user =(User)  session.getAttribute("user");
        if(null==user)
            return "fail";
        //获取所有订单项
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if(oi.getFood().getId().intValue()==fid){
                oi.setNumber(number);
                orderItemService.update(oi);
                break;
            }

        }
        return "success";
    }

    //删除订单项
    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem( Model model,HttpSession session,int oiid){
        User user =(User)  session.getAttribute("user");
        if(null==user)
            return "fail";
        orderItemService.delete(oiid);
        return "success";
    }

    //生成订单
    @RequestMapping("forecreateOrder")
    public String createOrder( Model model,Order order,HttpSession session){
        User user =(User)  session.getAttribute("user");
        //生成订单号！
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
        //设置订单属性
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUid(user.getId());
        order.setStatus(OrderService.waitPay);
        //获取订单项
        List<OrderItem> ois= (List<OrderItem>)  session.getAttribute("ois");

        float total =orderService.add(order,ois);
        return "redirect:forealipay?oid="+order.getId() +"&total="+total;
    }

    //支付成功页面
    @RequestMapping("forepayed")
    public String payed(int oid, float total, Model model) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        model.addAttribute("o", order);
        return "fore/payed";
    }

    //我的订单
    @RequestMapping("forebought")
    public String bought( Model model,HttpSession session) {
        User user =(User)  session.getAttribute("user");
        //查询未被删除的所有订单
        List<Order> os= orderService.list(user.getId(),OrderService.delete);

        //填充订单
        orderItemService.fill(os);

        model.addAttribute("os", os);

        return "fore/bought";
    }

    //确认收货
    @RequestMapping("foreconfirmPay")
    public String confirmPay( Model model,int oid) {
        //获取订单
        Order o = orderService.get(oid);
        //填充订单
        orderItemService.fill(o);
        model.addAttribute("o", o);
        return "fore/confirmPay";
    }

    //确认收货成功
    @RequestMapping("foreorderConfirmed")
    public String orderConfirmed( Model model,int oid) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.waitReview);
        o.setConfirmDate(new Date());
        orderService.update(o);
        return "fore/orderConfirmed";
    }

    //删除订单
    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder( Model model,int oid){
        Order o = orderService.get(oid);
        o.setStatus(OrderService.delete);
        orderService.update(o);
        return "success";
    }

    //评价
    @RequestMapping("forereview")
    public String review( Model model,int oid) {
        //获取订单
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        //获取菜品
        Food food = o.getOrderItems().get(0).getFood();
        //获取food的评价
        List<Review> reviews = reviewService.list(food.getId());
        //设置该food的评价属性
        foodService.setSaleAndReviewNumber(food);
        model.addAttribute("food", food);
        model.addAttribute("o", o);
        model.addAttribute("reviews", reviews);
        return "fore/review";
    }

    //提交评价
    @RequestMapping("foredoreview")
    public String doreview( Model model,HttpSession session,@RequestParam("oid") int oid,@RequestParam("fid") int fid,String content) {
        //获取order，设置order状态
        Order o = orderService.get(oid);
        o.setStatus(OrderService.finish);
        orderService.update(o);
        //获取菜品
        Food food = foodService.get(fid);
        content = HtmlUtils.htmlEscape(content);
        //获取user
        User user =(User)  session.getAttribute("user");
        //设置评价
        Review review = new Review();
        review.setContent(content);
        review.setFid(fid);
        review.setCreateDate(new Date());
        review.setUid(user.getId());
        reviewService.add(review);
        return "redirect:forereview?oid="+oid+"&showonly=true";
    }
}

package xgl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xgl.pojo.Canteen;
import xgl.pojo.Food;
import xgl.pojo.Shop;
import xgl.service.CanteenService;
import xgl.service.FoodService;
import xgl.service.ShopService;
import xgl.util.Page;

import java.util.List;

@Controller
@RequestMapping("")
public class FoodController {
    @Autowired
    FoodService foodService;
    @Autowired
    ShopService shopService;
    @Autowired
    CanteenService canteenService;

    /**
     * 增加菜品
     * @return
     */
    @RequestMapping("/food_add")
    public String add(Food food){
        foodService.add(food);
        return "redirect:/food_list?sid="+food.getSid();
    }

    /**
     * 删除菜品
     * @param id
     * @return
     */
    @RequestMapping("/food_delete")
    public String delete(int id){
        Food food = foodService.get(id);
        foodService.delete(id);
        return "redirect:/food_list?sid="+food.getSid();
    }

    /**
     * 修改菜品
     * @param food
     * @return
     */
    @RequestMapping("/food_update")
    public String update(Food food){
        foodService.update(food);
        return "redirect:/food_list?sid="+food.getSid();
    }

    /**
     * 编辑菜品
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/food_edit")
    public String edit(int id, Model model){
        Food food = foodService.get(id);
        model.addAttribute("food",food);
        return "admin/editFood";
    }

    /**
     * 查询某窗口下的所有菜品
     * @param model
     * @return
     */
    @RequestMapping("/food_list")
    public String list(Model model, Page page,int sid){
        //设置分页对象
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //查询所有，查询语句要在分页后面执行！
        List<Food> foods = foodService.list(sid);
        int total = (int) new PageInfo<>(foods).getTotal();
        page.setTotal(total);

        //分页需要sid参数
        String herfPara="sid="+sid;
        model.addAttribute("herfPara",herfPara);
        //把查询结果集对象foods和分页对象page，放到request域
        model.addAttribute("page",page);
        model.addAttribute("foods",foods);

       //面包屑导航栏要用，canteen，shop
        Shop shop = shopService.get(sid);
        Canteen canteen = canteenService.get(shop.getCid());
        model.addAttribute("shop",shop);
        model.addAttribute("canteen",canteen);
        return "admin/listFood";
    }

}

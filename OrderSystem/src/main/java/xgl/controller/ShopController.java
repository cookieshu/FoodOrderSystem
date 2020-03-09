package xgl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xgl.pojo.Canteen;
import xgl.pojo.Shop;
import xgl.service.CanteenService;
import xgl.service.ShopService;
import xgl.util.Page;
import java.util.List;

@Controller
@RequestMapping("")
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    CanteenService canteenService;

    /**
     * 增加窗口
     * @return
     */
    @RequestMapping("/shop_add")
    public String add(Shop shop){
        shopService.add(shop);
        return "redirect:/shop_list?cid="+shop.getCid();
    }

    /**
     * 删除窗口
     * @param id
     * @return
     */
    @RequestMapping("/shop_delete")
    public String delete(int id){
        Shop shop = shopService.get(id);
        shopService.delete(id);
        return "redirect:/shop_list?cid="+shop.getCid();
    }

    /**
     * 修改菜品
     * @param shop
     * @return
     */
    @RequestMapping("/shop_update")
    public String update(Shop shop){
        shopService.update(shop);
        return "redirect:/shop_list?cid="+shop.getCid();
    }

    /**
     * 编辑菜品
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/shop_edit")
    public String edit(int id, Model model){
        Shop shop = shopService.get(id);
        model.addAttribute("shop",shop);
        return "admin/editShop";
    }

    @RequestMapping("/shop_list")
    public String list(Model model, Page page,int cid){
        //设置分页对象
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //查询所有，查询语句要在分页后面执行！
        List<Shop> shops = shopService.list(cid);
        int total = (int) new PageInfo<>(shops).getTotal();
        page.setTotal(total);
        //分页需要cid参数
        String herfPara="cid="+cid;
        model.addAttribute("herfPara",herfPara);
        //把查询结果集对象foods和分页对象page，放到request域
        model.addAttribute("page",page);
        model.addAttribute("shops",shops);
        //add方法要用cid,导航栏要用到canteen名称
        Canteen canteen = canteenService.get(cid);
        model.addAttribute("canteen",canteen);
        return "admin/listShop";
    }



}

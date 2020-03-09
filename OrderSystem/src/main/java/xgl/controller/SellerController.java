package xgl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xgl.pojo.Canteen;
import xgl.pojo.Seller;
import xgl.pojo.Shop;
import xgl.service.CanteenService;
import xgl.service.SellerService;
import xgl.service.ShopService;
import xgl.util.Page;

import java.util.List;

@Controller
@RequestMapping("")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @Autowired
    ShopService shopService;
    @Autowired
    CanteenService canteenService;

    /**
     * 增加店主
     * @return
     */
    @RequestMapping("/seller_add")
    public String add(Seller seller){
        sellerService.add(seller);
        return "redirect:/seller_list";
    }

    /**
     * 删除店主
     * @param id
     * @return
     */
    @RequestMapping("/seller_delete")
    public String delete(int id){
        sellerService.delete(id);
        return "redirect:/seller_list";
    }

    /**
     * 修改店主
     * @param seller
     * @return
     */
    @RequestMapping("/seller_update")
    public String update(Seller seller){
        sellerService.update(seller);
        return "redirect:/seller_list";
    }

    /**
     * 编辑店主信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/seller_edit")
    public String edit(int id, Model model){
        Seller seller = sellerService.get(id);
        model.addAttribute("seller",seller);
        return "admin/editSeller";
    }

    /**
     * 店主列表
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/seller_list")
    public String sellers(Model model, Page page,int sid){
        //设置分页对象
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //查询所有，查询语句要在分页后面执行！
        List<Seller> sellers = sellerService.list(sid);
        int total = (int) new PageInfo<>(sellers).getTotal();
        page.setTotal(total);

        //分页需要sid参数
        String herfPara="sid="+sid;
        model.addAttribute("herfPara",herfPara);

        //把查询结果集对象foods和分页对象page，放到request域
        model.addAttribute("page",page);
        model.addAttribute("sellers",sellers);
        //面包屑导航栏所需对象，canteen，shop
        Shop shop = shopService.get(sid);
        Canteen canteen = canteenService.get(shop.getCid());
        model.addAttribute("shop",shop);
        model.addAttribute("canteen",canteen);

        return "admin/listSeller";
    }
}

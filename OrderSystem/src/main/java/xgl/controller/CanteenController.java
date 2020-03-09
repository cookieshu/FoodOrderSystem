package xgl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xgl.pojo.Canteen;
import xgl.pojo.Food;
import xgl.service.CanteenService;
import xgl.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("")
public class CanteenController {
    @Autowired
    CanteenService canteenService;

    /**
     * 增加食堂
     * @return
     */
    @RequestMapping("/canteen_add")
    public String add(Canteen canteen){
        canteenService.add(canteen);
        return "redirect:/canteen_list";
    }

    /**
     * 删除食堂
     * @param id
     * @return
     */
    @RequestMapping("/canteen_delete")
    public String delete(int id){
        canteenService.delete(id);
        return "redirect:/canteen_list";
    }

    /**
     * 修改食堂
     * @param canteen
     * @return
     */
    @RequestMapping("/canteen_update")
    public String update(Canteen canteen){
        canteenService.update(canteen);
        return "redirect:/canteen_list";
    }

    /**
     * 编辑食堂
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/canteen_edit")
    public String edit(int id, Model model){
        Canteen canteen = canteenService.get(id);
        model.addAttribute("canteen",canteen);
        return "admin/editCanteen";
    }


    /**
     * 查询所有菜品
     * @param model
     * @return
     */
    @RequestMapping("/canteen_list")
    public String list(Model model, Page page){
        //设置分页对象
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //查询所有，查询语句要在分页后面执行！
        List<Canteen> canteens = canteenService.list();
        int total = (int) new PageInfo<>(canteens).getTotal();
        page.setTotal(total);

        //把查询结果集对象foods和分页对象page，放到request域
        model.addAttribute("page",page);
        model.addAttribute("canteens",canteens);
        return "admin/listCanteen";
    }
}

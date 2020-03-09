package xgl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xgl.pojo.User;
import xgl.service.UserService;
import xgl.util.Page;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/user_list")
    public String list(Model model, Page page){
        //设置分页
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //调用方法查询
        List<User> users = userService.list();
        //获取总查询数量，设置分页
        int total =(int) new PageInfo<>(users).getTotal();
        page.setTotal(total);
        //把users结果集和page分页对象放入request域
        model.addAttribute("users",users);
        model.addAttribute("page",page);
        return "admin/listUser";
    }
}

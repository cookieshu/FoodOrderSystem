package xgl.controller;

import org.apache.ibatis.annotations.Many;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xgl.pojo.Canteen;
import xgl.pojo.Food;
import xgl.pojo.FoodImage;
import xgl.pojo.Shop;
import xgl.service.CanteenService;
import xgl.service.FoodImageService;
import xgl.service.FoodService;
import xgl.service.ShopService;
import xgl.util.ImageUtil;
import xgl.util.Page;
import xgl.util.UploadedImageFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("")
public class FoodImageController {
    @Autowired
    FoodImageService foodImageService;
    @Autowired
    FoodService foodService;
    @Autowired
    ShopService shopService;
    @Autowired
    CanteenService canteenService;

    /*因为只有图片，就不提供编辑和修改功能。 要做修改，先删除，再增加即可*/

    /**
     * 查询该菜品下，的所有图片
     * @param model
     * @param fid   传入food的id值，编辑图片的链接应该在listFood
     * @return
     */
    @RequestMapping("/foodImage_list")
    public String list(Model model, int fid){
        //获取该菜品
        Food food = foodService.get(fid);
        //获取foodImage
        List<FoodImage> single = foodImageService.list(fid, FoodImageService.type_single);
        List<FoodImage> detail = foodImageService.list(fid, FoodImageService.type_detail);
        //把以上数据放入request域中
        model.addAttribute("single",single);
        model.addAttribute("detail",detail);
        //导航栏要，canteen，shop，food，
        Shop shop = shopService.get(food.getSid());
        Canteen canteen = canteenService.get(shop.getCid());
        model.addAttribute("food",food);
        model.addAttribute("shop",shop);
        model.addAttribute("canteen",canteen);
        return "admin/listFoodImage";
    }

    /**
     * 增加
     * @param foodImage
     * @param request
     * @param uploadedImageFile
     * @return
     */
    @RequestMapping("/foodImage_add")
    public String add(FoodImage  foodImage, HttpServletRequest request, UploadedImageFile uploadedImageFile) {
        //存储上传的图片
        //mybatis插入自增主键ID的数据后返回自增的ID,对应的mapper.xml的insert语句下，要加入 keyProperty="id" useGeneratedKeys="true"
        foodImageService.add(foodImage);
        //1，设置文件名
        String fileName = foodImage.getId()+ ".jpg";
        //2,设置存储路径
        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;
        if(FoodImageService.type_single.equals(foodImage.getType())){
            imageFolder= request.getSession().getServletContext().getRealPath("img/foodSingle");
            imageFolder_small= request.getSession().getServletContext().getRealPath("img/foodSingle_small");
            imageFolder_middle= request.getSession().getServletContext().getRealPath("img/foodSingle_middle");
        }
        else{
            imageFolder= request.getSession().getServletContext().getRealPath("img/foodDetail");
        }

        File f = new File(imageFolder, fileName);
        f.getParentFile().mkdirs();
        try {
            uploadedImageFile.getImage().transferTo(f);
            BufferedImage img = ImageUtil.change2jpg(f);
            ImageIO.write(img, "jpg", f);

            if(FoodImageService.type_single.equals(foodImage.getType())) {
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);
                //修改图片大小
                ImageUtil.resizeImage(f, 56, 56, f_small);
                ImageUtil.resizeImage(f, 217, 190, f_middle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:foodImage_list?fid="+foodImage.getFid();
    }

    @RequestMapping("/foodImage_delete")
    public String delete(int id,HttpServletRequest request) {
        //获取要删除的foodImage
        FoodImage foodImage = foodImageService.get(id);
        // 文件名及路径
        String fileName = foodImage.getId()+ ".jpg";
        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;

        if(FoodImageService.type_single.equals(foodImage.getType())){
            imageFolder= request.getSession().getServletContext().getRealPath("img/foodSingle");
            imageFolder_small= request.getSession().getServletContext().getRealPath("img/foodSingle_small");
            imageFolder_middle= request.getSession().getServletContext().getRealPath("img/foodSingle_middle");
            File imageFile = new File(imageFolder,fileName);
            File f_small = new File(imageFolder_small,fileName);
            File f_middle = new File(imageFolder_middle,fileName);
            imageFile.delete();
            f_small.delete();
            f_middle.delete();
        }
        else{
            imageFolder= request.getSession().getServletContext().getRealPath("img/foodDetail");
            File imageFile = new File(imageFolder,fileName);
            imageFile.delete();
        }

        foodImageService.delete(id);

        return "redirect:foodImage_list?fid="+foodImage.getFid();
    }

}

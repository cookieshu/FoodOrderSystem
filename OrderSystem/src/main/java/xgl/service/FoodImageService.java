package xgl.service;

import xgl.pojo.FoodImage;

import java.util.List;

public interface FoodImageService {
    String type_single = "type_single";//菜品主图片
    String type_detail = "type_detail";//菜品详情图片

    void add(FoodImage foodImage);
    void delete(int id);
    void update(FoodImage foodImage);

    FoodImage get(int id);
    //根据菜品id和类型查询
    List<FoodImage>list(int fid,String type);
}

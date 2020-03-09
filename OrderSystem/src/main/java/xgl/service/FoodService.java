package xgl.service;

import xgl.pojo.Food;
import xgl.pojo.Shop;

import java.util.List;

public interface FoodService {
    void add(Food food);
    void delete(int id);
    void update(Food food);
    Food get(int id);

    //根据sid，获取该shop下的所有food
    List<Food>list(int sid);

    //设置菜品图片
    void setFirstFoodImage(Food f);

     void fill(List<Shop> shops);
     void fill(Shop shop);
     void fillByRow(List<Shop> shops);

     //设置菜品的销量和评价数量
     void setSaleAndReviewNumber(Food food);
    void setSaleAndReviewNumber(List<Food> foods);

    //根据关键字查询
    List<Food> search(String keyword);
}

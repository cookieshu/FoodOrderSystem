package xgl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xgl.mapper.FoodMapper;
import xgl.pojo.Food;
import xgl.pojo.FoodExample;
import xgl.pojo.FoodImage;
import xgl.pojo.Shop;
import xgl.service.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodMapper foodMapper;
    @Autowired
    FoodImageService foodImageService;
    @Autowired
    ShopService shopService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    @Override
    public void add(Food food) {
        foodMapper.insert(food);
    }

    @Override
    public void delete(int id) {
        foodMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Food food) {
        foodMapper.updateByPrimaryKeySelective(food);
    }

    //设置Shop属性
    private void setShop(Food food){
        Shop shop = shopService.get(food.getSid());
        food.setShop(shop);
    }
    private void setShop(List<Food> foods){
        for (Food food:foods){
            setShop(food);
        }
    }

    @Override
    public Food get(int id) {
        Food food = foodMapper.selectByPrimaryKey(id);
        //设置shop属性
        setShop(food);
        //设置firstFoodImage属性
        setFirstFoodImage(food);
        return food;
    }

    @Override
    public List<Food> list(int sid) {
        FoodExample example=new FoodExample();
        example.createCriteria().andSidEqualTo(sid);
        List<Food> foods = foodMapper.selectByExample(example);
        //设置属性
        setFirstFoodImage(foods);
        setShop(foods);
        return foods ;
    }

    @Override
    public void setFirstFoodImage(Food f) {
        //1,根据fid和图片类型查询出所有的单个图片，然后把第一个取出来放在firstFoodImage上。
        List<FoodImage> foodImages = foodImageService.list(f.getId(), FoodImageService.type_single);
        if (!foodImages.isEmpty()){
            f.setFirstFoodImage(foodImages.get(0));
        }
    }
    public void setFirstFoodImage(List<Food>foods){
        for (Food food:foods){
            setFirstFoodImage(food);
        }
    }

    //为多个店铺填充菜品
    @Override
    public void fill(List<Shop> shops) {
        for (Shop shop:shops){
            fill(shop);
        }
    }

    //为店铺填充菜品
    @Override
    public void fill(Shop shop) {
        //1，查询出该店铺的菜品
        List<Food> foods = list(shop.getId());
        //2,设置shop的菜品集合
        shop.setFoods(foods);
    }

    //为多个店铺填充推荐菜品集合
    @Override
    public void fillByRow(List<Shop> shops) {
        //每行个数
        int foodNumberEachRow = 4;
        for (Shop shop : shops) {
            //获取该店铺的所有菜品
            List<Food> foods =  shop.getFoods();

            List<List<Food>> foodsByRow =  new ArrayList<>();
            //遍历该foods集合，进行分组
            for (int i = 0; i < foods.size(); i+=foodNumberEachRow) {
                int size = i+foodNumberEachRow;
                size= size>foods.size()?foods.size():size;
                //把每一行的菜品加到集合里
                List<Food> foodsOfEachRow =foods.subList(i, size);
                foodsByRow.add(foodsOfEachRow);
            }
            //设置
            shop.setFoodsByRow(foodsByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Food food) {
        int saleCount = orderItemService.getSaleCount(food.getId());
        food.setSaleCount(saleCount);
        int reviewCount = reviewService.getCount(food.getId());
        food.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Food> foods) {
        for (Food f:foods)
            setSaleAndReviewNumber(f);
    }

    @Override
    public List<Food> search(String keyword) {
        FoodExample example=new FoodExample();
        //模糊查询
        example.createCriteria().andNameLike("%"+keyword+"%");
        example.setOrderByClause("id desc");
        List<Food> foods = foodMapper.selectByExample(example);
        setFirstFoodImage(foods);
        setShop(foods);
        return foods;
    }

}

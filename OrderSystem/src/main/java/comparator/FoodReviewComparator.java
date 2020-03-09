package comparator;

import xgl.pojo.Food;

import java.util.Comparator;
//人气比较器
//把 评价数量多的放前面
public class FoodReviewComparator implements Comparator<Food> {
    @Override
    public int compare(Food o1, Food o2) {
        return o2.getReviewCount()-o1.getReviewCount();
    }
}

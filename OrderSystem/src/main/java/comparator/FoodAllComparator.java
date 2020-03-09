package comparator;

import xgl.pojo.Food;

import java.util.Comparator;

//综合比较器;    把 销量x评价 高的放前面
public class FoodAllComparator implements Comparator<Food>{
    @Override
    public int compare(Food o1, Food o2) {
        return o2.getSaleCount()*o2.getReviewCount()-o1.getSaleCount()*o1.getReviewCount();
    }
}

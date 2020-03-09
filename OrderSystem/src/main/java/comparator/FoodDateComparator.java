package comparator;

import xgl.pojo.Food;

import java.util.Comparator;

//新品比较器
public class FoodDateComparator implements Comparator<Food>{
    @Override
    public int compare(Food o1, Food o2) {
        return o2.getCreateDate().compareTo(o1.getCreateDate());
    }
}

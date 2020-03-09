package comparator;

import xgl.pojo.Food;


import java.util.Comparator;

public class FoodSaleCountComparator implements Comparator<Food> {
    @Override
    public int compare(Food p1, Food p2) {
        return p2.getSaleCount()-p1.getSaleCount();
    }
}

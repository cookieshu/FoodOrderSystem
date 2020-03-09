package comparator;

import xgl.pojo.Food;

import java.util.Comparator;

public class FoodPriceComparator implements Comparator<Food> {

    @Override
    public int compare(Food p1, Food p2) {
        return (int) (p2.getPrice()-p1.getPrice());
    }

}
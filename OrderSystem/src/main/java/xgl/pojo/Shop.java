package xgl.pojo;

import java.util.List;

public class Shop {
    private Integer id;

    private String name;

    private Integer sid;

    private Integer cid;

    //非数据库字段
    //1，窗口下的多种菜品
    List<Food>foods;
    //2,一个店铺会对应多行菜品，而一行菜品里又有多个菜品。
    List<List<Food>>foodsByRow;

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<List<Food>> getFoodsByRow() {
        return foodsByRow;
    }

    public void setFoodsByRow(List<List<Food>> foodsByRow) {
        this.foodsByRow = foodsByRow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
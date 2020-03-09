package xgl.pojo;

import java.util.List;

public class Canteen {
    private Integer id;

    private String name;

    //非数据库字段
    //1,该食堂的所有店铺
    private List<Shop>shops;
    //2,按行推荐的店铺
    private List<List<Shop>> shopsByRow;

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public List<List<Shop>> getShopsByRow() {
        return shopsByRow;
    }

    public void setShopsByRow(List<List<Shop>> shopsByRow) {
        this.shopsByRow = shopsByRow;
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
}
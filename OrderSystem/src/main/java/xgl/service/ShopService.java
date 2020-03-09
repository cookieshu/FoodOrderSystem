package xgl.service;

import xgl.pojo.Canteen;
import xgl.pojo.Shop;

import java.util.List;

public interface ShopService {
    void add(Shop shop);
    void delete(int id);
    void update(Shop shop);
    Shop get(int id);

    //根据cid查询该食堂下的shops
    List<Shop> list(int cid);

    //把店铺填充食堂里
    void fill(Canteen canteen);
    void fill(List<Canteen> canteens);
    void fillByRow(List<Canteen> canteens);
}

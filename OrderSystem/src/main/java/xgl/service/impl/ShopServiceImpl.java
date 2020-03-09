package xgl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xgl.mapper.ShopMapper;
import xgl.pojo.Canteen;
import xgl.pojo.Shop;
import xgl.pojo.ShopExample;
import xgl.service.ShopService;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopMapper shopMapper;

    @Override
    public void add(Shop shop) {
        shopMapper.insert(shop);
    }

    @Override
    public void delete(int id) {
        shopMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Shop shop) {
        shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public Shop get(int id) {
        return shopMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Shop> list(int cid) {
        ShopExample example=new ShopExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id ASC");
        return shopMapper.selectByExample(example);
    }

    @Override
    public void fill(Canteen canteen) {
        //1,获取该食堂的所有店铺
        List<Shop> shops = list(canteen.getId());
        //2,设置
        canteen.setShops(shops);
    }

    @Override
    public void fill(List<Canteen> canteens) {
        for (Canteen canteen:canteens){
            fill(canteen);
        }
    }

    @Override
    public void fillByRow(List<Canteen> canteens) {
        int shopNumberEachRow = 5;
        for (Canteen canteen : canteens) {
            //1,获取该canteen下的所有shop
            List<Shop> shops = canteen.getShops();

            List<List<Shop>> shopsByRow =  new ArrayList<>();
            for (int i = 0; i < shops.size(); i+=shopNumberEachRow) {
                int size = i+shopNumberEachRow;
                size= size>shops.size()?shops.size():size;
                //设置每一行的shops
                List<Shop> shopsOfEachRow =shops.subList(i, size);
                shopsByRow.add(shopsOfEachRow);
            }
            canteen.setShopsByRow(shopsByRow);
        }
    }
}

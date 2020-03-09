package xgl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xgl.mapper.SellerMapper;
import xgl.pojo.Seller;
import xgl.pojo.SellerExample;
import xgl.service.SellerService;

import java.util.List;
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerMapper sellerMapper;

    @Override
    public void add(Seller seller) {
        sellerMapper.insert(seller);
    }

    @Override
    public void delete(int id) {
        sellerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Seller seller) {
        sellerMapper.updateByPrimaryKeySelective(seller);
    }

    @Override
    public Seller get(int id) {
        return sellerMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<Seller> list(int sid) {
        SellerExample example=new SellerExample();
        example.createCriteria().andSidEqualTo(sid);
        return sellerMapper.selectByExample(example);
    }
}

package xgl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xgl.mapper.CanteenMapper;
import xgl.pojo.Canteen;
import xgl.pojo.CanteenExample;
import xgl.service.CanteenService;

import java.util.List;

@Service
public class CanteenServiceImpl implements CanteenService {
    @Autowired
    CanteenMapper canteenMapper;

    @Override
    public void add(Canteen canteen) {
        canteenMapper.insert(canteen);
    }

    @Override
    public void delete(int id) {
        canteenMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Canteen canteen) {
        canteenMapper.updateByPrimaryKeySelective(canteen);
    }

    @Override
    public Canteen get(int id) {
        return canteenMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Canteen> list() {
        CanteenExample example=new CanteenExample();
        example.setOrderByClause("id ASC");
        return canteenMapper.selectByExample(example);
    }
}

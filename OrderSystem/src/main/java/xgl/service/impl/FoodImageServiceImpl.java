package xgl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xgl.mapper.FoodImageMapper;
import xgl.pojo.FoodImage;
import xgl.pojo.FoodImageExample;
import xgl.service.FoodImageService;

import java.util.List;
@Service
public class FoodImageServiceImpl implements FoodImageService {
    @Autowired
    FoodImageMapper foodImageMapper;

    @Override
    public void add(FoodImage foodImage) {
        foodImageMapper.insert(foodImage);
    }

    @Override
    public void delete(int id) {
        foodImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(FoodImage foodImage) {
        foodImageMapper.updateByPrimaryKeySelective(foodImage);
    }

    @Override
    public FoodImage get(int id) {
        return foodImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FoodImage> list(int fid, String type) {
        FoodImageExample example=new FoodImageExample();
        example.createCriteria().andFidEqualTo(fid).andTypeEqualTo(type);
        example.setOrderByClause("id ASC");
        List<FoodImage> foodImages = foodImageMapper.selectByExample(example);
        return foodImages;
    }
}

package xgl.mapper;

import java.util.List;
import xgl.pojo.FoodImage;
import xgl.pojo.FoodImageExample;

public interface FoodImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FoodImage record);

    int insertSelective(FoodImage record);

    List<FoodImage> selectByExample(FoodImageExample example);

    FoodImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FoodImage record);

    int updateByPrimaryKey(FoodImage record);
}
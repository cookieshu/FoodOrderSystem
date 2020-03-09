package xgl.mapper;

import java.util.List;
import xgl.pojo.Canteen;
import xgl.pojo.CanteenExample;

public interface CanteenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Canteen record);

    int insertSelective(Canteen record);

    List<Canteen> selectByExample(CanteenExample example);

    Canteen selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Canteen record);

    int updateByPrimaryKey(Canteen record);
}
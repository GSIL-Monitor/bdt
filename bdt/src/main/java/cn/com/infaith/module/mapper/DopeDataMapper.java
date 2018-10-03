package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.DopeData;

import java.util.List;

public interface DopeDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DopeData record);

    DopeData selectByPrimaryKey(Integer id);

    List<DopeData> selectAll();

    int updateByPrimaryKey(DopeData record);
}
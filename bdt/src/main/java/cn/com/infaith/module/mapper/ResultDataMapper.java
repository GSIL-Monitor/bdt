package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.ResultData;

import java.util.List;

public interface ResultDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResultData record);

    ResultData selectByPrimaryKey(Integer id);

    List<ResultData> selectAll();

    int updateByPrimaryKey(ResultData record);
}
package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.StatusData;
import java.util.List;

public interface StatusDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StatusData record);

    StatusData selectByPrimaryKey(Integer id);

    List<StatusData> selectAll();

    int updateByPrimaryKey(StatusData record);
}
package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TableLjzjzData;
import java.util.List;

public interface TableLjzjzDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TableLjzjzData record);

    TableLjzjzData selectByPrimaryKey(Integer id);

    List<TableLjzjzData> selectAll(String adminId);

    int updateByPrimaryKey(TableLjzjzData record);
}
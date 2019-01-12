package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TableLjzjzData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableLjzjzDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TableLjzjzData record);

    TableLjzjzData selectByPrimaryKey(Integer id);

    List<TableLjzjzData> selectAll(@Param("adminId") String adminId, @Param("type") int type);

    int updateByPrimaryKey(TableLjzjzData record);
}
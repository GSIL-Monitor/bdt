package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TableData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TableData record);

    TableData selectByPrimaryKey(Integer id);

    List<TableData> selectAll();

    int updateByPrimaryKey(TableData record);

    int addTableDataList(@Param("list") List<TableData> tableDataList);

    List<TableData> getTableInfo();
}
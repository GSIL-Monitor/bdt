package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TableMergeData;
import java.util.List;

public interface TableMergeDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TableMergeData record);

    TableMergeData selectByPrimaryKey(Integer id);

    List<TableMergeData> selectAll();

    int updateByPrimaryKey(TableMergeData record);

    TableMergeData getLastTableMergeDataNotId(int id);
}
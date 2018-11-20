package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TableMergeData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TableMergeDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TableMergeData record);

    TableMergeData selectByPrimaryKey(Integer id);

    List<TableMergeData> selectAll();

    int updateByPrimaryKey(TableMergeData record);

    TableMergeData getLastTableMergeDataNotId(@Param("id") int id, @Param("adminId") String adminId);

    List<Map<Integer, String>> getResultInfo(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                             @Param("adminId") String adminId);

    List<Map<Integer, String>> getLJZJZ(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                        @Param("adminId") String adminId);

    String getLjzjzByDate(@Param("adminId") String adminId, @Param("createTime") Date createTime);
}
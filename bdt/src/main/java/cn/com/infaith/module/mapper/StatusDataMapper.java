package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.StatusData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatusDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StatusData record);

    StatusData selectByPrimaryKey(Integer id);

    List<StatusData> selectAll();

    int updateByPrimaryKey(StatusData record);

    int addStatusDataList(@Param("list") List<StatusData> statusDataList);

    StatusData getStatusByTableNo(int tableNo);

    int updateStatusByTableNo(@Param("tableNo") int tableNo, @Param("status") int status);
}
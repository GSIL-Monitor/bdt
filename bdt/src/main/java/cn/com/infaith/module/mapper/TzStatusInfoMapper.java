package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TzStatusInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TzStatusInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TzStatusInfo record);

    TzStatusInfo selectByPrimaryKey(@Param("adminId") String adminId, @Param("tableNo") Integer tableNo,
                                    @Param("tzxt") Integer tzxt);

    List<TzStatusInfo> selectAll();

    int updateStatus(@Param("id") Integer id, @Param("tzStatus") Integer tzStatus);
}
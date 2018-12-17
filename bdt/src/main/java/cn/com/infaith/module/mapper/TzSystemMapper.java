package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TzSystem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TzSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TzSystem record);

    TzSystem selectByPrimaryKey(Integer id);

    List<TzSystem> selectAll();

    int updateByPrimaryKey(TzSystem record);

    int updateStartOrClose(@Param("started") Boolean started, @Param("tzxt") int tzxt,
                           @Param("fha") Integer fha,
                           @Param("fhb") Integer fhb,
                           @Param("fhc") Integer fhc,
                           @Param("fhd") Integer fhd,
                           @Param("xh") String xh,
                           @Param("adminId") String adminId);

    TzSystem getTzSystemInfo(@Param("tzxt") int tzxt, @Param("adminId") String adminId);
}
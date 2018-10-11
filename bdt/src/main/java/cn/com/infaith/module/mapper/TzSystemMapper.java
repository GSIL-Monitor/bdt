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
                           @Param("fh") Integer fh,
                           @Param("xh") String xh,
                           @Param("tableNo") Integer tableNo);

    TzSystem getTzSystemInfo(@Param("tzxt") int tzxt, @Param("tableNo") int tableNo);
}
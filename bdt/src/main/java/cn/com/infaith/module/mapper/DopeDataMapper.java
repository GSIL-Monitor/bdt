package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.DopeData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DopeDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DopeData record);

    DopeData selectByPrimaryKey(Integer id);

    List<DopeData> selectAll();

    int updateByPrimaryKey(DopeData record);

    int getDopeCountByTableNo(@Param("tableNo") int tableNo, @Param("tzSystem") int tzSystem);

    boolean clearAllDopeByTableNoAndTzSystem(@Param("tableNo") int tableNo, @Param("tzSystem") int tzSystem);

    DopeData getFirstDopeByTableNoAndTzSystem(@Param("tableNo") int tableNo, @Param("tzSystem") int tzSystem);
    DopeData getFirstDopeByTableNoAndTzSystemOrderByAccount(@Param("tableNo") int tableNo, @Param("tzSystem") int tzSystem);

    List<DopeData> getDopeByTableNoAndTzSystem(@Param("tableNo") int tableNo, @Param("tzSystem") int tzSystem);

    int addDopeDataList(@Param("list") List<DopeData> list);
}
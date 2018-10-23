package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.DopeManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DopeManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DopeManage record);

    DopeManage selectByPrimaryKey(Integer id);

    List<DopeManage> selectAll();

    int updateByPrimaryKey(DopeManage record);

    int insertList(@Param("list") List<DopeManage> list);

    List<DopeManage> getDopeMangeList(@Param("tzxt") int tzxt, @Param("adminId") String adminId);

    Integer getDopeManageIdByTzzh(@Param("tzzh") String tzzh, @Param("tzxt") Integer tzxt);

    List<DopeManage> getDopeManageByTableNoAndTzxt(@Param("tableNo") String tableNo, @Param("tzxt") int tzxt, @Param("adminId") String adminId);

    int deleteDopeManageByUserId(@Param("userId") String userId, @Param("adminId") String adminId);

    int updateDopeManageCheckByUserId(@Param("userId") String userId, @Param("hasCheck") Boolean hasCheck);
}
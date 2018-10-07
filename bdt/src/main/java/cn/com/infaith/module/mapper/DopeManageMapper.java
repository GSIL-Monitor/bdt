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

    List<DopeManage> getDopeMangeList(@Param("tzxt") int tzxt);

    Integer getDopeManageIdByTzzh(String tzzh);

}
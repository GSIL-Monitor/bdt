package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.DopeManageLogo;
import java.util.List;

public interface DopeManageLogoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DopeManageLogo record);

    DopeManageLogo selectByPrimaryKey(Integer id);

    List<DopeManageLogo> selectAll();

    int updateByPrimaryKey(DopeManageLogo record);
}
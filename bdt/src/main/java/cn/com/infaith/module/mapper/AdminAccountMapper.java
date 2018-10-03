package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.AdminAccount;

import java.util.List;

public interface AdminAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(AdminAccount record);

    AdminAccount selectByPrimaryKey(String id);

    List<AdminAccount> selectAll();

    int updateByPrimaryKey(AdminAccount record);
}
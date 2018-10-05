package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.AdminManageUser;
import cn.com.infaith.module.model.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminManageUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminManageUser record);

    int insertList(@Param("list") List<AdminManageUser> list);

    AdminManageUser selectByPrimaryKey(Integer id);

    List<AdminManageUser> selectAll();

    int updateByPrimaryKey(AdminManageUser record);

    List<UserAccount> getUserByAdmin(String adminId);

    int deleteAdminManageUser(String id);

    int checkoutCountByAdminAndUser(@Param("adminId") String adminId, @Param("userId") String userId);
}
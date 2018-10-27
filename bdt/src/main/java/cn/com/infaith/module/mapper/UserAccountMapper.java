package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserAccount record);

    UserAccount selectByPrimaryKey(String id);

    UserAccount selectByAccountAndPassWord(@Param("account") String account, @Param("password") String password, @Param("adminId") String adminId);

    int selectCountByAccount(@Param("account") String account, @Param("adminId") String adminId);

    List<UserAccount> selectAll();

    int updateByPrimaryKey(UserAccount record);

    List<UserAccount> selectByAdmin(String adminId);

    List<String> getUserIdByAdmin(String adminId);

    int editUpdateTime(String userId);

    int updateLoginStatusTrue();

    int updateLoginStatusFalse();
}
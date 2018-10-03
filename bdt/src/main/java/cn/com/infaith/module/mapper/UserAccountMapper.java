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

    UserAccount selectByAccountAndPassWord(@Param("account") String account, @Param("password") String password);

    int selectCountByAccount(String account);

    List<UserAccount> selectAll();

    int updateByPrimaryKey(UserAccount record);
}
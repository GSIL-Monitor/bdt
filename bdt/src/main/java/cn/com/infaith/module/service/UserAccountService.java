package cn.com.infaith.module.service;

import cn.com.infaith.module.model.AdminAccount;
import cn.com.infaith.module.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAccountService {

    /**
     * 添加百家乐账号
     * @param userAccount
     * @return
     */
    Boolean addUserAccount(UserAccount userAccount);

    /**
     * 删除百家乐账号
     * @param userId
     * @return
     */
    Boolean deleteUserAccount(String userId);

    /**
     * 修改百家乐账号信息
     * @param userAccount
     * @return
     */
    Boolean updateUserAccount(UserAccount userAccount);

    /**
     * 查看百家乐账号信息
     * @param userId
     * @return
     */
    UserAccount getUserAccount(String userId);

    /**
     * 通过账号和密码查询百加乐账号
     * @param account
     * @param passWord
     * @return
     */
    UserAccount getUserByAccountAndPassWord(String account, String passWord);

    /**
     * 添加后台管理账号
     * @param adminAccount
     * @return
     */
    Boolean addAdminAccount(AdminAccount adminAccount);

    /**
     * 修改后台管理账号信息
     * @param adminAccount
     * @return
     */
    Boolean updateAdminAccount(AdminAccount adminAccount);

    /**
     * 删除后台管理账号
     * @param adminId
     * @return
     */
    Boolean deleteAdminAccount(String adminId);

    /**
     * 查看后台管理账号信息
     * @param adminId
     * @return
     */
    AdminAccount getAdminAccount(String adminId);

    /**
     * 查看管理员所管理的用户
     * @param adminId
     * @return
     */
    List<UserAccount> getUserByAdmin(String adminId);

    /**
     * 添加管理员所管理的用户
     * @param adminId
     * @param userId
     * @return
     */
    Boolean addAdminManageUser(String adminId, String userId);

    /**
     * 删除管理员所管理的用户
     * @param id
     * @return
     */
    Boolean deleteAdminManageUser(String id);
}

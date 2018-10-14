package cn.com.infaith.module.service.impl;

import cn.com.infaith.module.mapper.AdminAccountMapper;
import cn.com.infaith.module.mapper.AdminManageUserMapper;
import cn.com.infaith.module.mapper.UserAccountMapper;
import cn.com.infaith.module.model.AdminAccount;
import cn.com.infaith.module.model.AdminManageUser;
import cn.com.infaith.module.model.UserAccount;
import cn.com.infaith.module.service.UserAccountService;
import cn.com.infaith.module.util.MD5Util;
import cn.com.infaith.module.util.PublicUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private AdminAccountMapper adminAccountMapper;
    @Autowired
    private AdminManageUserMapper adminManageUserMapper;

    @Override
    public String addUserAccount(UserAccount userAccount) {

        int count = userAccountMapper.selectCountByAccount(userAccount.getAccount());
        if (count > 0) {
            return "";
        }
        String id = PublicUtil.getUUID();
        userAccount.setId(id);
        userAccount.setPassword(MD5Util.encrypt(userAccount.getPassword()));
        int result = userAccountMapper.insert(userAccount);
        if (result > 0) {
            return id;
        }
        return null;
    }

    @Override
    public Boolean deleteUserAccount(String userId) {
        return userAccountMapper.deleteByPrimaryKey(userId) > 0 ? true : false;
    }

    @Override
    public Boolean updateUserAccount(UserAccount userAccount) {

        if (StringUtils.isNotBlank(userAccount.getPassword())) {
            userAccount.setPassword(MD5Util.encrypt(userAccount.getPassword()));
        }
        return userAccountMapper.updateByPrimaryKey(userAccount) > 0 ? true : false;
    }

    @Override
    public UserAccount getUserAccount(String userId) {
        return userAccountMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserAccount getUserByAccountAndPassWord(String account, String passWord) {
        passWord = MD5Util.encrypt(passWord);
        return userAccountMapper.selectByAccountAndPassWord(account, passWord);
    }

    @Override
    public Boolean addAdminAccount(AdminAccount adminAccount) {
        adminAccount.setId(PublicUtil.getUUID());
        adminAccount.setPassword(MD5Util.encrypt(adminAccount.getPassword()));
        return adminAccountMapper.insert(adminAccount) > 0 ? true : false;
    }

    @Override
    public Boolean updateAdminAccount(AdminAccount adminAccount) {

        if (StringUtils.isNotBlank(adminAccount.getPassword())) {
            adminAccount.setPassword(MD5Util.encrypt(adminAccount.getPassword()));
        }
        return adminAccountMapper.updateByPrimaryKey(adminAccount) > 0 ? true : false;
    }

    @Override
    public Boolean deleteAdminAccount(String adminId) {
        return adminAccountMapper.deleteByPrimaryKey(adminId) > 0 ? true : false;
    }

    @Override
    public AdminAccount getAdminAccount(String adminId) {
        return adminAccountMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public List<UserAccount> getUserByAdmin(String adminId) {
        return adminManageUserMapper.getUserByAdmin(adminId);
    }

    @Override
    public Boolean addAdminManageUser(String adminId, String userId) {

        List<AdminManageUser> list = new ArrayList<>();
        List<String> userIdList = Arrays.asList(userId.split(","));
        userIdList.stream().forEach(x -> {
            AdminManageUser adminManageUser = new AdminManageUser();
            adminManageUser.setId(PublicUtil.getUUID());
            adminManageUser.setAdminId(adminId);
            adminManageUser.setUserId(x);
            list.add(adminManageUser);
        });
        return adminManageUserMapper.insertList(list) > 0 ? true : false;
    }

    @Override
    public Boolean deleteAdminManageUser(String id) {
        return adminManageUserMapper.deleteAdminManageUser(id) > 0 ? true : false;
    }

    @Override
    public AdminAccount getAdminByAccountAndPassword(String account, String password) {

        password = MD5Util.encrypt(password);
        return adminAccountMapper.getAdminByAccountAndPassword(account, password);
    }

    @Override
    public int checkoutCountByAdminAndUser(String adminId, String userIds) {

        String[] userId = userIds.split(",");
        AtomicInteger count = new AtomicInteger(0);
        Arrays.asList(userId).forEach(x -> {
            int result = adminManageUserMapper.checkoutCountByAdminAndUser(adminId, x);
            if (result > 0) {
                count.getAndIncrement();
                return;
            }
        });
        return count.get();
    }
}

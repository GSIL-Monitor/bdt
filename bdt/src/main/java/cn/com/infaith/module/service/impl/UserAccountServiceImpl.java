package cn.com.infaith.module.service.impl;

import cn.com.infaith.module.mapper.AdminAccountMapper;
import cn.com.infaith.module.mapper.AdminManageUserMapper;
import cn.com.infaith.module.mapper.DopeManageMapper;
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
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private DopeManageMapper dopeManageMapper;

    @Override
    public String addUserAccount(UserAccount userAccount) {

        int count = userAccountMapper.selectCountByAccount(userAccount.getAccount(), userAccount.getAdminId());
        if (count > 0) {
            return "";
        }
        String id = PublicUtil.getUUID();
        userAccount.setId(id);
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

            userAccount.setPassword(userAccount.getPassword());
        return userAccountMapper.updateByPrimaryKey(userAccount) > 0 ? true : false;
    }

    @Override
    public UserAccount getUserAccount(String userId) {
        return userAccountMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserAccount getUserByAccountAndPassWord(String account, String passWord, String adminId) {
        passWord = MD5Util.encrypt(passWord);
        return userAccountMapper.selectByAccountAndPassWord(account, passWord, adminId);
    }

    @Override
    public String addAdminAccount(AdminAccount adminAccount) {
        String id = PublicUtil.getUUID();
        adminAccount.setId(id);
        adminAccount.setPassword(MD5Util.encrypt(adminAccount.getPassword()));
        if (adminAccountMapper.insert(adminAccount) > 0) {
            return id;
        } else {
            return null;
        }
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
        return userAccountMapper.selectByAdmin(adminId);
    }

    @Override
    public List<String> getUserIdByAdmin(String adminId) {
        return userAccountMapper.getUserIdByAdmin(adminId);
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

    @Override
    public int deleteDopeManageByUserId(String userId, String adminId) {
        return dopeManageMapper.deleteDopeManageByUserId(userId, adminId);
    }

    @Override
    @Transactional
    public void deleteUserByIdCommit(String userId, String adminId) {
        deleteUserAccount(userId);
        deleteDopeManageByUserId(userId, adminId);
    }

    @Override
    public List<String> getAllAdminId() {
        return adminAccountMapper.getAllAdminId();
    }

    @Override
    public List<AdminAccount> getAllAdminInfo() {
        return adminAccountMapper.getAllAdminInfo();
    }

    @Override
    public int updateLoginStatusTrue() {
        return userAccountMapper.updateLoginStatusTrue();
    }

    @Override
    public int updateLoginStatusFalse() {
        return userAccountMapper.updateLoginStatusFalse();
    }

    @Override
    public int updateRequestStatus(String userId, Boolean requestStatus) {
        return userAccountMapper.updateRequestStatus(userId, requestStatus);
    }

    @Override
    public List<AdminAccount> selectAllAdmin() {
        return adminAccountMapper.selectAll();
    }

    @Override
    public List<UserAccount> selectAll() {
        return userAccountMapper.selectAll();
    }

    @Override
    public int selectRequestCount(String userId) {
        return userAccountMapper.selectRequestCount(userId);
    }
}

package cn.com.infaith.module.controller;

import cn.com.infaith.module.model.AdminAccount;
import cn.com.infaith.module.model.UserAccount;
import cn.com.infaith.module.service.UserAccountService;
import cn.com.infaith.module.util.ResponseJsonUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class UserController {

    @Autowired
    private UserAccountService userAccountService;

    @ApiOperation(value = "登录百家乐账号", notes = "登录百家乐账号", httpMethod = "POST")
    @PostMapping("/loginUserAccount")
    public JSONObject loginUserAccount(@RequestParam String account, @RequestParam String password) {

        UserAccount userAccount = userAccountService.getUserByAccountAndPassWord(account, password);
        if (userAccount == null) {
            return ResponseJsonUtil.getResponseJson(404, "not find", null);
        } else {
            //修改登录状态
            userAccount.setLoginStatus(true);
            userAccountService.updateUserAccount(userAccount);
            return ResponseJsonUtil.getResponseJson(200, "登录成功", userAccount);
        }
    }

    @ApiOperation(value = "退出百家乐账号", notes = "退出百家乐账号", httpMethod = "POST")
    @PostMapping("/exitUserAccount")
    public JSONObject exitUserAccount(@RequestParam String userId) {
        //修改登录状态
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userId);
        userAccount.setLoginStatus(false);
        userAccountService.updateUserAccount(userAccount);
        return ResponseJsonUtil.getResponseJson(200, "退出成功", null);
    }

    @ApiOperation(value = "添加百家乐账号", notes = "添加百家乐账号", httpMethod = "POST")
    @PostMapping("/addUserAccount")
    public JSONObject addUserAccount(@ModelAttribute UserAccount userAccount) {

        Boolean result = userAccountService.addUserAccount(userAccount);
        if (result == null) {
            return ResponseJsonUtil.getResponseJson(-2, "已存在", userAccount);
        }
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", userAccount);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }

    @ApiOperation(value = "删除百家乐账号", notes = "删除百家乐账号", httpMethod = "DELETE")
    @DeleteMapping("/deleteUserAccount")
    public JSONObject deleteUserAccount(@RequestParam String userId) {

        Boolean result = userAccountService.deleteUserAccount(userId);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }

    @ApiOperation(value = "修改百家乐账号信息", notes = "修改百家乐账号信息", httpMethod = "POST")
    @PostMapping("/editUserAccount")
    public JSONObject editUserAccount(@ModelAttribute UserAccount userAccount) {

        Boolean result = userAccountService.updateUserAccount(userAccount);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", userAccount);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }

    @ApiOperation(value = "查看百家乐账号信息", notes = "查看百家乐账号信息", httpMethod = "GET")
    @PostMapping("/getUserAccount")
    public JSONObject getUserAccount(@RequestParam String userId) {

        UserAccount account = userAccountService.getUserAccount(userId);
        if (account == null) {
            return ResponseJsonUtil.getResponseJson(404, "not find", null);
        }
        return ResponseJsonUtil.getResponseJson(200, "find", account);
    }

    @ApiOperation(value = "添加后台管理账号", notes = "添加后台管理账号", httpMethod = "POST")
    @PostMapping("/addAdminAccount")
    public JSONObject addAdminAccount(@ModelAttribute AdminAccount adminAccount) {

        Boolean result = userAccountService.addAdminAccount(adminAccount);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", adminAccount);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }

    @ApiOperation(value = "删除后台管理账号", notes = "删除后台管理账号", httpMethod = "DELETE")
    @DeleteMapping("/deleteAdminAccount")
    public JSONObject deleteAdminAccount(@RequestParam String userId) {

        Boolean result = userAccountService.deleteAdminAccount(userId);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }

    @ApiOperation(value = "修改管理员账号信息", notes = "修改管理员账号信息", httpMethod = "POST")
    @PostMapping("/editAdminAccount")
    public JSONObject editAdminAccount(@ModelAttribute AdminAccount adminAccount) {

        Boolean result = userAccountService.updateAdminAccount(adminAccount);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", adminAccount);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }

    @ApiOperation(value = "查看管理员账号信息", notes = "查看管理员账号信息", httpMethod = "GET")
    @GetMapping("/getAdminAccount")
    public JSONObject getAdminAccount(@RequestParam String adminId) {

        AdminAccount account = userAccountService.getAdminAccount(adminId);
        if (account == null) {
            return ResponseJsonUtil.getResponseJson(404, "not find", null);
        }
        return ResponseJsonUtil.getResponseJson(200, "find", account);
    }

    @ApiOperation(value = "查看管理员所管理的用户", notes = "查看管理员所管理的用户", httpMethod = "GET")
    @GetMapping("/getUserByAdmin")
    public JSONObject getUserByAdmin(@RequestParam String adminId) {
        List<UserAccount> list = userAccountService.getUserByAdmin(adminId);
        return ResponseJsonUtil.getResponseJson(200, "success", list);
    }

    @ApiOperation(value = "添加管理员所管理的用户", notes = "添加管理员所管理的用户", httpMethod = "POST")
    @PostMapping("/addAdminManageUser")
    public JSONObject addAdminManageUser(@RequestParam String adminId, @RequestParam String userIds) {

        Boolean result = userAccountService.addAdminManageUser(adminId, userIds);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }

    @ApiOperation(value = "删除管理员所管理的用户", notes = "删除管理员所管理的用户", httpMethod = "DELETE")
    @DeleteMapping("/deleteAdminManageUser")
    public JSONObject deleteAdminManageUser(@RequestParam String manageId) {

        Boolean result = userAccountService.deleteAdminManageUser(manageId);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }
}

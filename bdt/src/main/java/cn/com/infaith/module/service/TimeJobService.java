package cn.com.infaith.module.service;

import cn.com.infaith.module.model.TableLjzjzData;
import cn.com.infaith.module.model.UserAccount;
import cn.com.infaith.module.util.LogUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableScheduling
@Service
@Component
public class TimeJobService {

    @Autowired
    BJLDataService bjlDataService;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    TableDataService tableDataService;

    @Scheduled(cron = "*/5 * * * * ?")
    public void calcTzResult() {
        LogUtil.info(this.getClass(), "实时计算投注结果开始》》》》》");
        List<String> adminIdList = userAccountService.getAllAdminId();
        if (CollectionUtils.isNotEmpty(adminIdList)) {
            adminIdList.stream().forEach(x -> {
                int count = bjlDataService.calcTzResult(x);
                LogUtil.info(this.getClass(), "admin:"+ x +">>实时计算投注结果结束>>>>>>>更新投注数量：" + count);
            });
        }
    }

    @Scheduled(cron = "*/30 * * * * ?")
    public void calUserLoginStatus() {
        LogUtil.info(this.getClass(), "更新用户状态开始》》》》》");
        userAccountService.updateLoginStatusTrue();
        userAccountService.updateLoginStatusFalse();
    }

    @Scheduled(cron = "*/20 * * * * ?")
    public void calUpdateRequestStatus() {
        LogUtil.info(this.getClass(), "更新用户请求状态》》》》》");
        List<UserAccount> list = userAccountService.selectAll();
        for (int i = 0; i < list.size(); i++) {
            int count = userAccountService.selectRequestCount(list.get(i).getId());
            if (count > 0) {
                userAccountService.updateRequestStatus(list.get(i).getId(),true);
            } else {
                userAccountService.updateRequestStatus(list.get(i).getId(),false);
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void uploadExcel() {
        LogUtil.info(this.getClass(), "导出牌面信息至服务器开始》》》》》");
        LogUtil.info(this.getClass(), "导出牌面信息至服务器开始》》》》》");
        LogUtil.info(this.getClass(), "导出牌面信息至服务器开始》》》》》");
        tableDataService.addUploadFile(false);
        LogUtil.info(this.getClass(), "导出牌面信息至服务器结束》》》》》");
        LogUtil.info(this.getClass(), "导出牌面信息至服务器结束》》》》》");
        LogUtil.info(this.getClass(), "导出牌面信息至服务器结束》》》》》");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void uploadResultFile() {
        LogUtil.info(this.getClass(), "导出投注信息至服务器开始》》》》》");
        LogUtil.info(this.getClass(), "导出投注信息至服务器开始》》》》》");
        tableDataService.addUploadResultFile(false);
        LogUtil.info(this.getClass(), "导出投注信息至服务器结束》》》》》");
        LogUtil.info(this.getClass(), "导出投注信息至服务器结束》》》》》");
    }

    @Scheduled(cron = "0 0 12 ? * MON")
    public void uploadFile() {
        tableDataService.addUploadResultFile(true);
        tableDataService.addUploadFile(true);
    }

    @Scheduled(cron = "0 0/3 * * * ?")
    public void addLjzjzData() {
        if (tableDataService.jobStarted() == 1) {
            LogUtil.info(this.getClass(), "合并数据开始执行》》》》》");
            List<String> adminIdList = userAccountService.getAllAdminId();
            if (CollectionUtils.isNotEmpty(adminIdList)) {
                adminIdList.stream().forEach(adminId -> {
                    String ljzjz = tableDataService.getLastTableMergeData(adminId);
                    TableLjzjzData tableLjzjzData = new TableLjzjzData();
                    tableLjzjzData.setAdminId(adminId);
                    tableLjzjzData.setLjzjz(ljzjz);
                    tableDataService.addTableLjzjzData(tableLjzjzData);
                });
            }
        }

    }

    @Scheduled(cron = "30 1/3 * * * ?")
    public void addLjzjzData2() {
        if (tableDataService.jobStarted() == 1) {
            LogUtil.info(this.getClass(), "合并数据开始执行》》》》》");
            List<String> adminIdList = userAccountService.getAllAdminId();
            if (CollectionUtils.isNotEmpty(adminIdList)) {
                adminIdList.stream().forEach(adminId -> {
                    String ljzjz = tableDataService.getLastTableMergeData(adminId);
                    TableLjzjzData tableLjzjzData = new TableLjzjzData();
                    tableLjzjzData.setAdminId(adminId);
                    tableLjzjzData.setLjzjz(ljzjz);
                    tableDataService.addTableLjzjzData(tableLjzjzData);
                });
            }
        }
    }

    @Scheduled(cron = "0 15 13 ? * MON")
    public void startedAddLjzjz() {
        LogUtil.info(this.getClass(), "开启合并数据》》》》》");
        tableDataService.updateJobStarted(true);
        List<String> adminIdList = userAccountService.getAllAdminId();
        if (CollectionUtils.isNotEmpty(adminIdList)) {
            adminIdList.stream().forEach(adminId -> {
                String ljzjz = tableDataService.getLastTableMergeData(adminId);
                TableLjzjzData tableLjzjzData = new TableLjzjzData();
                tableLjzjzData.setAdminId(adminId);
                tableLjzjzData.setLjzjz(ljzjz);
                tableDataService.addTableLjzjzData(tableLjzjzData);
            });
        }
    }

    @Scheduled(cron = "0 0 11 ? * MON")
    public void closedAddLjzjz() {
        LogUtil.info(this.getClass(), "关闭合并数据》》》》》");
        tableDataService.updateJobStarted(false);
    }
}

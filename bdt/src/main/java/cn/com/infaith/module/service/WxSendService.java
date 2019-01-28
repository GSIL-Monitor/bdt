package cn.com.infaith.module.service;

import cn.com.infaith.module.mapper.WxSendRecordMapper;
import cn.com.infaith.module.model.AdminAccount;
import cn.com.infaith.module.model.TableData;
import cn.com.infaith.module.model.UserAccount;
import cn.com.infaith.module.model.WxSendRecord;
import cn.com.infaith.module.util.HttpConnectionUtil;
import cn.com.infaith.module.util.LogUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WxSendService {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private WxSendRecordMapper wxSendRecordMapper;
    @Autowired
    private TableDataService tableDataService;


    public void sendLoginOutMsg() {
        List<UserAccount> accountList = new ArrayList<>();
        List<String> adminNameList = new ArrayList<>();
        List<AdminAccount> adminIdList = userAccountService.getAllAdminInfo();
        for (AdminAccount adminAccount : adminIdList) {
            List<UserAccount> list = userAccountService.getUserByAdmin(adminAccount.getId());
            for (UserAccount user : list) {
                if (!user.getLoginStatus()) {
                    accountList.add(user);
                    adminNameList.add(adminAccount.getAccount());
                }
            }
        }
        String str = "账号：";
        String string = "Id：";
        for (UserAccount user : accountList) {
            str = str.concat(user.getName() + "  ");
            string = string.concat(user.getId() + "  ");
        }
        if (CollectionUtils.isNotEmpty(accountList)) {
            Integer count = wxSendRecordMapper.selectByType(1);
            if (count == null || count == 0) {
                HashSet<String> name = new HashSet(adminNameList);
                sendWxMsg("ofZMgwmlrFE7R01ZOJKW5gJZiE6w,ofZMgwt54mjaUne-64b4aGDHWa4k", "管理员：" + name.toString(),
                        "离线通知",
                        str,
                        string,
                        ">>>>>>>>",
                        "");
                WxSendRecord record = new WxSendRecord();
                record.setType(1);
                record.setContent(name.toString() + string);
                wxSendRecordMapper.insert(record);
            }
        }
    }

    public void sendReadMsg() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        List<AdminAccount> adminAccountList = userAccountService.getAllAdminInfo();
        Integer count = wxSendRecordMapper.selectByType(2);
        if (count == null || count == 0) {
            for (AdminAccount adminAccount : adminAccountList) {
                List<String> errorList = new ArrayList<>();
                for (int i = 1; i <= 12 ; i++) {
                    List<TableData> tableDataList = tableDataService.selectLasted10ByAdmin(adminAccount.getId(), i);
                    if (CollectionUtils.isNotEmpty(tableDataList)) {
                        tableDataList = tableDataList.stream().sorted(Comparator.comparing(TableData::getCreateTime))
                                .collect(Collectors.toList());
                        List<Integer> fitNoList = tableDataList.stream().map(TableData::getFitNo).collect(Collectors.toList());
                        Object[] arr = fitNoList.toArray();
                        for (int j = 0; j < arr.length - 1; j++) {
                            if (j != arr.length -1) {
                                if ((Integer)arr[j+1] != 1 && (Integer)arr[j+1] - (Integer)arr[j] != 1) {
                                    errorList.add("桌"+i+"局"+tableDataList.get(j).getBattleNo()+":错误副"+arr[j]+"~"+arr[j+1]+"-time:"+(sf.format(tableDataList.get(j).getCreateTime())));
                                }
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(errorList)) {
                    String errorStr = "";
                    for (String error : errorList) {
                        errorStr = errorStr.concat(error + ";");
                    }
                    sendWxMsg("ofZMgwmlrFE7R01ZOJKW5gJZiE6w,ofZMgwt54mjaUne-64b4aGDHWa4k", "管理员：" + adminAccount.getAccount(),
                            "读牌错误通知",
                            "",
                            errorList.toString(),
                            ">>>>>>>>",
                            "");
                    WxSendRecord record = new WxSendRecord();
                    record.setType(2);
                    record.setContent(errorList.toString());
                    wxSendRecordMapper.insert(record);
                }
            }
        }
    }




    public void sendWxMsg(String wxIds, String first, String messageType, String messageName, String messageContent,
                          String remark, String toUrl) {

        String url = "https://api.in-hope.cn/v1/wxsend/sendTestMsg";
        Map<String, Object> map = new HashMap<>();
        map.put("wxIds", wxIds);
        map.put("first", first);
        map.put("messageType", messageType);
        map.put("messageName", messageName);
        map.put("messageContent", messageContent);
        map.put("remark", remark);
        map.put("toUrl", toUrl);
//        url = url.concat("?wxIds="+wxIds);
//        url = url.concat("&first="+first);
//        url = url.concat("&messageType="+messageType);
//        url = url.concat("&messageName="+messageName);
//        url = url.concat("&messageContent="+messageContent);
//        url = url.concat("&remark="+remark);
//        url = url.concat("&toUrl="+toUrl);
        String result = HttpConnectionUtil.httpPostSendMap(url, map);
        LogUtil.info(WxSendService.class, "微信发送" + result);

    }

}

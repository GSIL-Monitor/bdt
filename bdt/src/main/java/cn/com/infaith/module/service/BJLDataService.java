package cn.com.infaith.module.service;

import cn.com.infaith.module.enums.ResultTzJgEnum;
import cn.com.infaith.module.enums.TableNoEnum;
import cn.com.infaith.module.enums.TableResultEnum;
import cn.com.infaith.module.enums.TableStatusEnum;
import cn.com.infaith.module.model.*;
import cn.com.infaith.module.util.LogUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BJLDataService {

    //    public final static List<Integer> tableNoList = Arrays.stream(TableNoEnum.values()).map(TableNoEnum::getIndex).collect(Collectors.toList());
    @Autowired
    private CalcXGLZGLServiceNotMap calcXGLZGLServiceNotMap;
    @Autowired
    private TableDataService tableDataService;
    @Autowired
    private UserAccountService userAccountService;

    public static Map<String, CalcXGLZGLServiceNotMap> calcList = new HashMap<>();

//    static {
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//        calcList.add(new CalcXGLZGLServiceNotMap());
//    }

    /**
     * 生成桌子初始数据
     *
     * @return
     */
    public void initStatusData(int tableNo) {
        StatusData statusData = new StatusData();
        statusData.setTableNo(tableNo);
        statusData.setBattleNo(1);
        statusData.setFitNo(1);
        statusData.setStatus(TableStatusEnum.NEW.getIndex());
        tableDataService.addStatusData(statusData);
    }

    /**
     * 步骤1：读取局号、副号、状态，并与《状态表》中同桌记录的局号、副号、状态比较。
     *
     * @return 0：进入步骤15   1：进入"可投注"状态  2：进入"新局准备"  3：进入"开牌"状态
     */
    public void JudgeState(TableData tableData) {
        tableData.setCreateTime(new Date(tableData.getCreateDate()));
        //获取当前桌的最新状态
//        StatusData statusData = tableDataService.getStatusByTableNo(tableData.getTableNo());
        int count = tableDataService.selectStatusByTable(tableData.getTableNo(), tableData.getBattleNo(),
                tableData.getFitNo(), tableData.getStatus());
        if (count == 0) {
            StatusRequest statusRequest = new StatusRequest();
            statusRequest.setTableNo(tableData.getTableNo());
            statusRequest.setBattleNo(tableData.getBattleNo());
            statusRequest.setFitNo(tableData.getFitNo());
            statusRequest.setStatus(tableData.getStatus());
            tableDataService.addStatusRequest(statusRequest);
            StatusData newStatus = new StatusData();
            newStatus.setTableNo(tableData.getTableNo());
            newStatus.setBattleNo(tableData.getBattleNo());
            newStatus.setFitNo(tableData.getFitNo());
            newStatus.setStatus(tableData.getStatus());
            tableDataService.updateStatus(newStatus);
            List<String> adminIds = userAccountService.getAllAdminId();
            adminIds.stream().forEach(adminId -> {
                tableData.setAdminId(adminId);
                BdtSystem bdtSystem = tableDataService.getBdtSystem(adminId);
                if (bdtSystem.getStarted()) {
                    //当前状态为“可投注”（局号、副号不会变）。
                    if (tableData.getStatus() == TableStatusEnum.TZ.getIndex()) {
                        tzStatus(tableData, adminId, true, statusRequest.getId());
                    }
                    //当前状态为“新局准备”（局号改变、副号变为1）。
                    if (tableData.getStatus() == TableStatusEnum.NEW.getIndex()) {
//                        newReadyStatus(tableData);
                        //第一副，新增第一副时实时的投注
                        tzStatus(tableData, adminId, false, statusRequest.getId());
                    }
                    //当前状态为“开牌”（局号、副号不会变）。
                    if (tableData.getStatus() == TableStatusEnum.KP.getIndex()) {
                        openCard(tableData, bdtSystem);
                    }
                    LogUtil.info(this.getClass(), "计算结束：>>>>>time:" + System.currentTimeMillis());
                }
            });
        }
    }

    /**
     * 可投注状态
     */
    public void tzStatus(TableData tableData, String adminId, Boolean tzNextFit, int statusId) {
        //查询《TZ1同桌号下单表》中有无记录。
//        int step2Result = step2(tableData.getTableNo(), tableData.getFitNo(), 1, adminId);
//        if (step2Result == 0) {
//            //无记录，进入步骤4
//            step4(tableData, adminId, tzNextFit);
//        } else {
        if (!tzNextFit) {
            //如果是第一副的新局准备，则需要把上一局最后一幅计算的下一幅给删除
//            StatusRequest status = tableDataService.getLastStatusRequest(statusId, tableData.getTableNo());
            TableData lastedTable = tableDataService.getNewestTableData(tableData.getTableNo(), adminId);
            if (lastedTable != null) {
                tableDataService.updateResult(lastedTable.getTableNo(), lastedTable.getBattleNo(), lastedTable.getFitNo() + 1);
            }
        }
        step4(tableData, adminId, tzNextFit);
//        } else {
        //有记录，当前副号>1。进入步骤3。
//            step3(tableData.getTableNo(), adminId);
//            tzStatus(tableData, adminId, tzNextFit);
//        }
    }

    /**
     * 新局准备状态
     */
    public void newReadyStatus(TableData tableData) {
        int result = step13(tableData.getTableNo());
        if (result == 1) {
//            step14(tableData);
        } else {
            tableDataService.clearAllDopeByTableNoAndTzSystem(tableData.getTableNo(), 1);
            step14(tableData);
        }
    }

    /**
     * 步骤13，查询《TZ1同桌号下单表》中有无记录。
     * 1）无记录。进入步骤14。
     * 2）有记录。删除所有记录。进入步骤14。
     *
     * @return 1      2
     */
    public int step13(int tableNo) {
        //查询《TZ1同桌号下单表》中有无记录
        int tzTotal = tableDataService.getDopeCountByTableNo(tableNo, 1, null);
        if (tzTotal == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 步骤14，将《状态表》同桌记录中的状态改为“新局准备”。进入步骤15。
     *
     * @param tableData
     */
    public void step14(TableData tableData) {
//        tableDataService.updateStatusByTableNo(tableData.getTableNo(), tableData.getBattleNo(), tableData.getFitNo(), TableStatusEnum.NEW.getIndex());
    }

    /**
     * 步骤2  查询《TZ1同桌号下单表》中有无记录。
     * 1）无记录。进入步骤4。
     * 2）有记录，当前副号=1。清除表中所有记录。进入步骤4。
     * 3）有记录，当前副号>1。进入步骤3。
     *
     * @param tableNo
     * @param fitNo
     * @param tzSystem
     * @return 0：无记录  1：有记录，当前副号=1   2：有记录，当前副号>1
     */
    private int step2(int tableNo, int fitNo, int tzSystem, String adminId) {
        //查询《TZ1同桌号下单表》中有无记录
        int tzTotal = tableDataService.getDopeCountByTableNo(tableNo, tzSystem, adminId);
        if (tzTotal > 0) {
            //有记录
            if (fitNo == 1) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }

    /**
     * 步骤3   取表中的第1条记录中的“账号、投注方向、投注金额”进行投注。
     *
     * @return
     */
    public void step3(int tableNo, String adminId) {
        //取表中的第1条记录中的“账号、投注方向、投注金额”进行投注
        DopeData dopeData = tableDataService.getFirstDopeByTableNoAndTzSystem(tableNo, 1, adminId);
        if (dopeData != null) {
            addResultAndDeleteDopeCommit(dopeData);
        }
    }

    /**
     * 添加结果表记录并删除下单表记录
     *
     * @param dopeData
     */
    @Transactional
    public void addResultAndDeleteDopeCommit(DopeData dopeData) {
        //如果投注成功，添加至投注结果表
        ResultData resultData = new ResultData();
        resultData.setCreateTime(dopeData.getCreateTime());
        resultData.setTableNo(dopeData.getTableNo());
        resultData.setBattleNo(dopeData.getBattleNo());
        resultData.setFitNo(dopeData.getFitNo());
        resultData.setTzfx(dopeData.getTzfx());
        resultData.setTzxt(dopeData.getTzxt());
        resultData.setTzzh(dopeData.getTzzh());
        resultData.setTzje(dopeData.getTzje().toString());
        resultData.setTzzt(null);
        tableDataService.addResultData(resultData);
        //并删除该记录
        tableDataService.deleteDopeDataById(dopeData.getId());
    }

    /**
     * 步骤4    投注子系统2是否启动。
     *
     * @return
     */
    public void step4(TableData tableData, String adminId, Boolean tzNextFit) {
        //获取投注系统2信息
        TzSystem tzSystem = tableDataService.getTzSystemInfo(2, adminId);
        if (tzSystem.getStarted()) {
            //处于“启动”状态，进入步骤5-1。
            int count = step5_1(tableData.getFitNo(), tzSystem);
            if (count == 1) {
                step6(tableData.getTableNo(), tableData.getBattleNo(), tableData.getFitNo());
            } else {
                step5_2(tableData, adminId, tzNextFit);
//                step5_3(tableData.getTableNo(), adminId);
//                int result = step5_4(tableData.getTableNo(), adminId);
//                if (result > 0) {
//                    for (int i = 0; i < result; i++) {
//                        step5_3(tableData.getTableNo(), adminId);
//                    }
//                }
            }
        } else {
            //处于“关闭”状态。进入步骤6。
            step6(tableData.getTableNo(), tableData.getBattleNo(), tableData.getFitNo());
        }
    }

    /**
     * 步骤5-1，在管理界面、投注子系统2中取参数TZ2FHA、TZ2FHB，比较当前副号与TZ2FHA、TZ2FHB。
     *
     * @param fitNo
     * @param tzSystem
     * @return 1 进入步骤6。  2  进入步骤5-2。
     */
    public int step5_1(int fitNo, TzSystem tzSystem) {
        if (fitNo < Integer.valueOf(tzSystem.getFha()) || fitNo > Integer.valueOf(tzSystem.getXh())) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 步骤5-2，根据当前时间、桌号，比较管理界面、投注子系统2中的账户参数，选取符合要求的账户，
     * 在《TZ2同桌号下单表》中生成记录，记录的信息包括“日期、时间、桌号、局号、副号、投注方向、投注系统、投注账号、投注金额”，
     * 其中“日期、时间、桌号、局号、副号”均为当前信息，“投注方向”取自管理界面、投注子系统2中的“方向选择”结果，
     * “投注系统”为TZ2，“投注账号、投注金额”取自管理界面、投注子系统2中的账户参数。关于时间、桌号的比较，
     * 桌号相同、当前时间满足投注时间限制1、当前时间的分钟满足投注时间限制2，才是符合要求的账户。若有多个账号，
     * 按账号顺序在下单表中排序。进入步骤5-3。
     */
    public void step5_2(TableData tableData, String adminId, Boolean tzNextFit) {
        List<DopeManage> dopeManageList = tableDataService.getDopeManageByTableNoAndTzxt(tableData.getTableNo().toString(), 2, adminId);
        List<DopeManage> list = parseDopeManage(dopeManageList);
        List<ResultData> resultDataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(dopeManage -> {
                boolean checkTime = checkDopeInfo(tableData.getCreateTime(), dopeManage.getTzsjSection1());
                if (checkTime) {
                    checkTime = checkDopeInfo2(tableData.getCreateTime(), dopeManage.getTzsjSection2());
                }
                if (checkTime) {
                    ResultData data = new ResultData();
                    data.setCreateTime(tableData.getCreateTime());
                    data.setTableNo(tableData.getTableNo());
                    data.setBattleNo(tableData.getBattleNo());
                    if (tzNextFit) {
                        data.setFitNo(tableData.getFitNo() + 1);
                    } else {
                        data.setFitNo(tableData.getFitNo());
                    }
                    data.setTzfx(dopeManage.getTzfx());
                    data.setTzje(dopeManage.getTzje().toString());
//                        data.setTzsjSection(dopeData.getTzsjSection1());
                    data.setTzxt(2);
                    data.setTzzh(dopeManage.getTzzh());
//                    data.setAdminId(adminId);
                    resultDataList.add(data);
                }
            });
        }
        if (CollectionUtils.isNotEmpty(resultDataList)) {
            tableDataService.addResultDataList(resultDataList);
        }
    }

    /**
     * 步骤5-3，按账号顺序取《TZ2同桌号下单表》中的第1条记录中的“账号、投注方向、投注金额”进行投注。
     *
     * @param tableNo
     */
    public void step5_3(int tableNo, String adminId) {
        //按账号顺序取《TZ2同桌号下单表》中的第1条记录中的“账号、投注方向、投注金额”进行投注。
        DopeData dopeData = tableDataService.getFirstDopeByTableNoAndTzSystemOrderByAccount(tableNo, 2, adminId);
        if (dopeData != null) {
            addResultAndDeleteDopeCommit(dopeData);
        }
    }

    /**
     * 步骤5-4，查询《TZ2同桌号下单表》中有无记录。
     * 1）无记录。进入步骤6。
     * 2）有记录。进入步骤5-3。
     *
     * @param tableNo
     * @return
     */
    public int step5_4(int tableNo, String adminId) {
        int count = tableDataService.getDopeCountByTableNo(tableNo, 2, adminId);
        return count;
    }

    /**
     * 步骤6，将《状态表》同桌记录中的状态改为“可投注”。进入步骤15
     *
     * @param tableNo
     * @param battleNo
     * @param fitNo
     */
    public void step6(int tableNo, int battleNo, int fitNo) {
//        tableDataService.updateStatusByTableNo(tableNo, battleNo, fitNo, TableStatusEnum.TZ.getIndex());
    }

    /**
     * 开牌状态
     */
    public void openCard(TableData tableData, BdtSystem bdtSystem) {

        boolean result = step7(tableData);
        if (!result) {
            return;
        }
        tableData = step8_1(tableData);
        tableData = step8_2(tableData, bdtSystem.getAdminId());
        TableMergeData tableMergeData = step8_3(tableData, bdtSystem);
        if (tableMergeData != null) {
            step8_4(tableMergeData);
        }
        TzSystem tz11System = tableDataService.getTzSystemInfo(11, tableData.getAdminId());
//        TableData tableDataNew = step10_1(tableData.getTableNo(), tableData.getAdminId());
        TableData tableDataNew = tableData;
        if (tz11System.getStarted()) {
            //投注子系统11启动
            //比较ZTSL与TZ1XH
            int step10_2Result = step10_2(tableDataNew.getZtsl(), tz11System);
            if (step10_2Result == 1) {
                //ZTSL>TZ1XH，进入步骤9-2
                step9_2(tableDataNew);
            } else {
                //生成投注记录
                step10_4(tableData, bdtSystem.getAdminId(), 11, TableResultEnum.Z.getIndex());
                //进入步骤9-2
                step9_2(tableDataNew);
            }
        } else {
            //处于“关闭”状态。进入步骤9-2。
            step9_2(tableDataNew);
        }
    }

    public void step9_2(TableData tableData) {
        TzSystem tz12System = tableDataService.getTzSystemInfo(12, tableData.getAdminId());
        if (tz12System.getStarted()) {
            //处于“启动”状态，进入步骤10-4。
            BigDecimal ztsl = new BigDecimal(tableData.getZtsl());
            BigDecimal xh = new BigDecimal(tz12System.getXh());
            int result = ztsl.compareTo(xh);
            if (result == 1) {
                //1）ZTSL>TZ12XH，进入步骤9-3。
                step9_3(tableData);
            } else {
                //2）ZTSL<=TZ12XH，进入步骤10-6。
                int status12 = step10_3(tableData, tz12System);
                if (status12 == 1) {
                    //1）TZ12FHA<=副号<=TZ12FHB或者TZ12FHC<=副号<=TZ12FHD，进入步骤10-7。
                    step10_4(tableData, tableData.getAdminId(), 12, TableResultEnum.Z.getIndex());
                    //进入步骤9-3。
                    step9_3(tableData);
                    step9_3(tableData);
                } else {
                    //2）副号不在1）的两个区间，进入步骤9-3。
                    step9_3(tableData);
                }
            }
        } else {
            //处于“关闭”状态。进入步骤9-3。
            step9_3(tableData);
        }
    }

    public void step9_3(TableData tableData) {
        TzSystem tz13System = tableDataService.getTzSystemInfo(13, tableData.getAdminId());
        if (tz13System.getStarted()) {
            //处于“启动”状态，进入步骤10-8。
            int result = step10_9(tableData, tz13System);
            if (result == 1) {
                //进入步骤10-10  : 生成投注记录在
                step10_4(tableData, tableData.getAdminId(), 13, TableResultEnum.Z.getIndex());
                //进入步骤9-4。
                step9_4(tableData);
            } else {
                //进入步骤9-4。
                step9_4(tableData);
            }
        } else {
            //处于“关闭”状态。进入步骤9-4。
            step9_4(tableData);
        }
    }

    public void step9_4(TableData tableData) {
        TzSystem tz14System = tableDataService.getTzSystemInfo(14, tableData.getAdminId());
        if (tz14System.getStarted()) {
            //比较XTSL与TZ14XH。
            int step10_2Result = step10_2(tableData.getXtsl(), tz14System);
            if (step10_2Result == 1) {
                //XTSL>TZ14XH，进入步骤9-5
                step9_5(tableData);
            } else {
                //生成投注记录
                step10_4(tableData, tableData.getAdminId(), 14, TableResultEnum.X.getIndex());
                //进入步骤9-5
                step9_5(tableData);
            }
        } else {
            //处于“关闭”状态。进入步骤9-2。
            step9_5(tableData);
        }
    }

    public void step9_5(TableData tableData) {
        TzSystem tz3System = tableDataService.getTzSystemInfo(3, tableData.getAdminId());
        if (tz3System.getStarted()) {
            //处于“启动”状态，进入步骤10-14。
            int result = step10_14(tableData, tz3System);
            if (result == 1) {
                step11_1();
            } else if (result == 2) {
                int result2 = step10_16(tableData, tz3System);
                if (result2 == 1) {
                    //生成投注记录
                    step10_4_1(tableData, tableData.getAdminId(), 3);
                } else {
                    step11_1();
                }
            } else if (result == 3) {
                TzStatusInfo tzStatusInfo = tableDataService.getTzStatus(tableData.getAdminId(), tableData.getTableNo(), 3);
                if (tzStatusInfo.getTzStatus() == 1) {
                    //生成投注记录
                    step10_4_1(tableData, tableData.getAdminId(), 3);
                } else {
                    step11_1();
                }
            } else if (result == 4) {
                TzStatusInfo tzStatusInfo = tableDataService.getTzStatus(tableData.getAdminId(), tableData.getTableNo(), 3);
                if (tzStatusInfo.getTzStatus() == 1) {
                    tableDataService.updateTzStatus(tzStatusInfo.getId(), 0);
                }
                step11_1();
            } else {
                step11_1();
            }
        } else {
            //处于“关闭”状态。进入步骤11-1。
            step11_1();
        }

    }

    /**
     * 1）TZ3CHC<=差值<=TZ3CHD，将《TZ3控制表》同桌号状态改为“可投注”，进入步骤10-20。
     * 2）差值<TZ3CHC或差值>TZ3CHD，将《TZ3控制表》同桌号状态改为“不投注”，进入步骤11-1。
     *
     * @param tableData
     * @param tzSystem
     */
    public int step10_16(TableData tableData, TzSystem tzSystem) {
        int count = tableDataService.getTableResultCalCount(tableData.getTableNo(), tableData.getBattleNo(), Integer.valueOf(tzSystem.getFha()));
        TzStatusInfo tzStatusInfo = tableDataService.getTzStatus(tableData.getAdminId(), tableData.getTableNo(), 3);
        if (count >= Integer.valueOf(tzSystem.getFhc()) && count <= Integer.valueOf(tzSystem.getFhd())) {
            if (tzStatusInfo.getTzStatus() == 0) {
                tableDataService.updateTzStatus(tzStatusInfo.getId(), 1);
            }
            return 1;
        } else {
            if (tzStatusInfo.getTzStatus() == 1) {
                tableDataService.updateTzStatus(tzStatusInfo.getId(), 0);
            }
            return 2;
        }
    }

    //投注并计算结果（已用job计算）
    public void step11_1() {
        //无
    }

    /**
     * 步骤7    读取牌面数据，包括牌面、结果，并将读取的结果记入《同桌号数据表》
     *
     * @param tableData 牌面数据
     */
    public Boolean step7(TableData tableData) {
        //将读取的结果记入《同桌号数据表》
        Integer id = tableDataService.addTableData(tableData);
        if (id == null) {
            return false;
        }
        return true;
    }

    /**
     * 步骤8-1   取《同桌号数据表》最新一条记录（即步骤7生成的记录）中的“副号、牌面”，并将牌面K、Q、J均取为0。
     */
    public TableData step8_1(TableData tableData) {
        //去最新一条记录
        String card = tableData.getCard();
        card = card.replaceAll("k|K|q|Q|j|J", "0");
        String xianCard = tableData.getXianCard();
        xianCard = xianCard.replaceAll("k|K|q|Q|j|J", "0");
        tableData.setCard(card);
        tableData.setXianCard(xianCard);
        return tableData;
    }

    /**
     * 步骤8-2   计算该副XGL、ZGL，XTSL、ZTSL。具体算法见附件。将计算结果记入《同桌号数据表》同条记录中
     *
     * @param tableData
     */
    public TableData step8_2(TableData tableData, String adminId) {

        BdtSystem system = tableDataService.getBdtSystem(adminId);
        Map<String, BigDecimal> map = new HashMap<>();
        if (tableData.getFitNo() == 1 || calcList.get(adminId + (tableData.getTableNo() - 1)) == null) {
            calcList.put(adminId + (tableData.getTableNo() - 1), new CalcXGLZGLServiceNotMap());
        }
        String card = tableData.getCard() + tableData.getXianCard();
        try {
            map = calcList.get(adminId + (tableData.getTableNo() - 1)).calcXgl(tableData.getFitNo(), system.getPs(), card, system.getPhxs());
        } catch (Exception e) {
            calcList.put(adminId + (tableData.getTableNo() - 1), new CalcXGLZGLServiceNotMap());
            TableData fitOneTable = tableDataService.getFitOneTable(tableData.getTableNo(), tableData.getBattleNo(), adminId);
            if (fitOneTable != null) {
                card = fitOneTable.getCard() + fitOneTable.getXianCard();
            }
            calcList.get(adminId + (tableData.getTableNo() - 1)).calcXgl(1, system.getPs(), card, system.getPhxs());
            map = calcList.get(adminId + (tableData.getTableNo() - 1)).calcXgl(tableData.getFitNo(), system.getPs(), card, system.getPhxs());
        }
        tableData.setXgl(map.get("xgl").toPlainString());
        tableData.setXtsl(map.get("xtsl").toPlainString());
        tableData.setZgl(map.get("zgl").toPlainString());
        tableData.setZtsl(map.get("ztsl").toPlainString());
        //更新数据
        tableDataService.updateTableData(tableData);
        return tableData;
    }

    /**
     * 步骤8-3   计算该副XJZ、ZJZ。取《同桌号数据表》同条记录中的“日期、时间、桌号、局号、副号、结果”
     * ，在管理界面、控制子系统中取参数PHXS。
     *
     * @param tableData
     * @return
     */
    public TableMergeData step8_3(TableData tableData, BdtSystem bdtSystem) {

        TableMergeData tableMergeData = new TableMergeData();
        tableMergeData.setCreateTime(tableData.getCreateTime());
        tableMergeData.setTableNo(tableData.getTableNo());
        tableMergeData.setBattleNo(tableData.getBattleNo());
        tableMergeData.setFitNo(tableData.getFitNo());
        tableMergeData.setIsDelete(false);
        tableMergeData.setAdminId(tableData.getAdminId());
        BigDecimal txxsCompare = new BigDecimal("0.012");
        //1）副号=1，且TXXS>=0.012，进入步骤8-3-5。
        //2）副号=1，且TXXS<0.012，不计算ZJZ、LJZJZ，进入步骤9-1。
        //3）副号>1，进入步骤8-3-3。
        if (tableData.getFitNo() == 1 && bdtSystem.getTxxs().compareTo(txxsCompare) >= 0) {
            //进入步骤8-3-5。
            return step8_3_5(tableData.getResult(), bdtSystem.getPhxs(), tableMergeData);
        } else if (tableData.getFitNo() == 1 && bdtSystem.getTxxs().compareTo(txxsCompare) == -1) {
            //进入步骤9-1。
            return null;
        } else if (tableData.getFitNo() > 1) {
            //进入步骤8-3-3。
            String ztslStr = tableDataService.getZtslByTable(tableData.getAdminId(), tableData.getTableNo(),
                    tableData.getBattleNo(), tableData.getFitNo());
            if (StringUtils.isBlank(ztslStr)) {
                return null;
            }
            BigDecimal ztsl = new BigDecimal(ztslStr);
            if (ztsl.compareTo(bdtSystem.getTxxs()) <= 0) {
                //进入步骤8-3-5。
                return step8_3_5(tableData.getResult(), bdtSystem.getPhxs(), tableMergeData);
            } else {
                return null;
            }
        } else {
            return null;
        }
//        int result = tableData.getResult();
//        if (result == TableResultEnum.Z.getIndex()) {
//            xjz = new BigDecimal(-1).add(phxs).setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
//            zjz = new BigDecimal(0.95).add(phxs).setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
//        } else if (result == TableResultEnum.X.getIndex()) {
//            xjz = new BigDecimal(1).add(phxs).setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
//            zjz = new BigDecimal(-1).add(phxs).setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
//        }
    }

    /**
     * 计算ZJZ
     *
     * @param result
     * @param phxs
     * @param tableMergeData
     * @return
     */
    public TableMergeData step8_3_5(int result, BigDecimal phxs, TableMergeData tableMergeData) {

        String xjz = "0";
        String zjz = "0";
        BigDecimal s = new BigDecimal("0.95");
        if (result == TableResultEnum.X.getIndex()) {
            zjz = phxs.subtract(BigDecimal.ONE).setScale(4, BigDecimal.ROUND_DOWN).toPlainString();
        } else if (result == TableResultEnum.Z.getIndex()) {
            zjz = s.multiply((phxs.add(BigDecimal.ONE))).setScale(4, BigDecimal.ROUND_DOWN).toPlainString();
        }
        tableMergeData.setXjz(xjz);
        tableMergeData.setZjz(zjz);
        tableDataService.addTableMergeData(tableMergeData);
        return tableMergeData;
    }

    /**
     * 步骤8-4，计算LJXJZ、LJZJZ。
     *
     * @param tableMergeData
     */
    public void step8_4(TableMergeData tableMergeData) {
        TableMergeData data = tableDataService.getLastTableMergeDataNotId(tableMergeData.getId(), tableMergeData.getAdminId());
        if (data == null) {
            tableMergeData.setLjxjz(tableMergeData.getXjz());
            tableMergeData.setLjzjz(tableMergeData.getZjz());
        } else {
            BigDecimal xjz = new BigDecimal(tableMergeData.getXjz());
            BigDecimal zjz = new BigDecimal(tableMergeData.getZjz());
            BigDecimal ljxjz = new BigDecimal(data.getLjxjz());
            BigDecimal ljzjz = new BigDecimal(data.getLjzjz());
            tableMergeData.setLjxjz(xjz.add(ljxjz).setScale(4, BigDecimal.ROUND_DOWN).toPlainString());
            tableMergeData.setLjzjz(zjz.add(ljzjz).setScale(4, BigDecimal.ROUND_DOWN).toPlainString());
        }
        tableDataService.updateTableMergeData(tableMergeData);
    }

    /**
     * 步骤9，投注子系统1是否启动。
     *
     * @return 返回投注系统信息
     */
    public TzSystem step9(String adminId) {
        TzSystem tzSystem = tableDataService.getTzSystemInfo(1, adminId);
        return tzSystem;
    }

    /**
     * 步骤10-1，取《同桌号数据表》最新一条记录中的“副号、ZTSL”，在管理界面、投注子系统1中取参数TZ1FH、TZ1XH。
     */
    public TableData step10_1(int tableNo, String adminId) {
        TableData tableData = tableDataService.getNewestTableData(tableNo, adminId);
        return tableData;
    }

    /**
     * 步骤10-2，比较 _tsl 与TZ1XH
     *
     * @param tsl
     * @param tzSystem
     * @return 1）ZTSL>TZ1XH，进入步骤9-2。
     * 2）ZTSL<=TZ11XH，进入步骤10-3。
     */
    public int step10_2(String tsl, TzSystem tzSystem) {
        BigDecimal ztsl = new BigDecimal(tsl);
        BigDecimal xh = new BigDecimal(tzSystem.getXh());
        int result = ztsl.compareTo(xh);
        if (result == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 步骤10-3，比较ZTSL与TZ1XH。
     *
     * @param tableData
     * @param tzSystem
     * @return 1）TZ1FHA<=副号<=TZ1FHB或者TZ1FHC<=副号<=TZ1FHD，进入步骤10-4。
     * 2）副号不在1）的两个区间，进入步骤11-1。
     */
    public int step10_3(TableData tableData, TzSystem tzSystem) {
        Boolean result1 = tableData.getFitNo().compareTo(Integer.valueOf(tzSystem.getFha())) >= 0 ? true : false;
        Boolean result2 = tableData.getFitNo().compareTo(Integer.valueOf(tzSystem.getFhb())) <= 0 ? true : false;
        Boolean result3 = tableData.getFitNo().compareTo(Integer.valueOf(tzSystem.getFhc())) >= 0 ? true : false;
        Boolean result4 = tableData.getFitNo().compareTo(Integer.valueOf(tzSystem.getFhd())) <= 0 ? true : false;
        if ((result1 && result2) || (result3 && result4)) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 步骤10-9，比较ZTSL与TZ13XHA、TZ13XHB、TZ13XHC、TZ13XHD。
     * 1）TZ13XHA<ZTSL<=TZ13XHB或者TZ13XHC<ZTSL<=TZ13XHD，进入步骤10-10。
     * 2）ZTSL不在1）的两个区间，进入步骤9-4。
     *
     * @param tableData
     * @param tzSystem
     * @return
     */
    public int step10_9(TableData tableData, TzSystem tzSystem) {
        BigDecimal ztsl = new BigDecimal(tableData.getZtsl());
        BigDecimal xha = new BigDecimal(tzSystem.getFha());
        BigDecimal xhb = new BigDecimal(tzSystem.getFhb());
        BigDecimal xhc = new BigDecimal(tzSystem.getFhc());
        BigDecimal xhd = new BigDecimal(tzSystem.getFhd());
        Boolean result1 = ztsl.compareTo(xha) == 1 ? true : false;
        Boolean result2 = ztsl.compareTo(xhb) <= 0 ? true : false;
        Boolean result3 = ztsl.compareTo(xhc) == 1 ? true : false;
        Boolean result4 = ztsl.compareTo(xhd) <= 0 ? true : false;
        if ((result1 && result2) || (result3 && result4)) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 步骤10-15，比较副号与TZ3FHA、TZ3FHB。
     * <p>
     * 1）副号<TZ3FHA，进入步骤11-1。
     * 2）副号=TZ3FHA，进入步骤10-16。
     * 3) TZ3FHA<副号<TZ3FHB，进入步骤10-19。
     * 4) 副号=TZ3FHB，进入步骤10-21。
     * 5) 副号>TZ3FHB，进入步骤11-1。
     *
     * @param tableData
     * @param tzSystem
     * @return
     */
    public int step10_14(TableData tableData, TzSystem tzSystem) {
        Integer fha = Integer.valueOf(tzSystem.getFha());
        Integer fhb = Integer.valueOf(tzSystem.getFhb());
        if (tableData.getFitNo() < fha) {
            return 1;
        } else if (tableData.getFitNo() == fha) {
            return 2;
        } else if (tableData.getFitNo() > fha && tableData.getFitNo() < fhb) {
            return 3;
        } else if (tableData.getFitNo() == fhb) {
            return 4;
        } else {
            return 5;
        }
    }

    /**
     * 遍历分解dopeManage
     *
     * @param list
     * @return
     */
    public List<DopeManage> parseDopeManage(List<DopeManage> list) {
        List<DopeManage> dopeManageList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(x -> {
                String[] tzsjSection1 = StringUtils.isNotBlank(x.getTzsjSection1()) ? x.getTzsjSection1().split(",") : null;
                for (int i = 0; i < tzsjSection1.length; i++) {
                    DopeManage dopeManage = new DopeManage();
                    dopeManage.setId(x.getId());
                    dopeManage.setTzxt(x.getTzxt());
                    dopeManage.setTzzh(x.getTzzh());
                    dopeManage.setTzje(x.getTzje());
                    dopeManage.setTzfx(x.getTzfx());
                    dopeManage.setTzsjSection1(tzsjSection1[i]);
                    dopeManage.setTzsjSection2(x.getTzsjSection2());
                    dopeManage.setTableNo(x.getTableNo());
                    dopeManageList.add(dopeManage);
                }
            });
        }
        return dopeManageList;
    }

    /**
     * 步骤10-4，取同条记录中的“时间、桌号”，比较管理界面、投注子系统1中的账户参数，选取符合要求的账户，
     * 在《TZ1同桌号下单表》中生成记录，记录的信息包括“日期、时间、桌号、局号、副号、投注方向、投注系统、投注账号、投注金额”，
     * 其中“日期、时间、桌号、局号”均为《同桌号数据表》最新一条记录中的信息，
     * “副号”为该条记录中的副号+1，“投注方向”为“庄”，“投注系统”为TZ1，“投注账号、投注金额”取自管理界面、投注子系统1中的账户参数。
     * 关于时间、桌号的比较，桌号相同、当前时间满足投注时间限制，才是符合要求的账户。若有多个账号，按账号顺序在下单表中排序。
     *
     * @param tableData
     */
    public void step10_4(TableData tableData, String adminId, int tzxt, int tzfx) {

        List<DopeManage> dopeManage = tableDataService.getDopeManageByTableNoAndTzxt(tableData.getTableNo().toString(), tzxt, adminId);
        List<DopeManage> list = parseDopeManage(dopeManage);
        //先获取该桌号、投注系统的账户
        if (!CollectionUtils.isEmpty(list)) {
            List<ResultData> resultDataList = new ArrayList<>();
            TableData tableDataNew = tableDataService.getNewestTableData(tableData.getTableNo(), adminId);
            list.stream().forEach(dopeData -> {
                //判断当前时间是否满足投注时间限制且投注桌号一致
                boolean timeResult = checkDopeInfo(tableData.getCreateTime(), dopeData.getTzsjSection1());
                if (timeResult) {
                    ResultData data = new ResultData();
                    data.setCreateTime(tableDataNew.getCreateTime());
                    data.setTableNo(tableDataNew.getTableNo());
                    data.setBattleNo(tableDataNew.getBattleNo());
                    data.setFitNo(tableDataNew.getFitNo() + 1);
                    data.setTzje(dopeData.getTzje().toString());
                    data.setTzxt(tzxt);
                    data.setTzfx(tzfx);
                    data.setTzzh(dopeData.getTzzh());
                    data.setAdminId(adminId);
                    resultDataList.add(data);
                }
            });
            if (CollectionUtils.isNotEmpty(resultDataList)) {
                tableDataService.addResultDataList(resultDataList);
            }
        }
    }

    public void step10_4_1(TableData tableData, String adminId, int tzxt) {

        List<DopeManage> dopeManage = tableDataService.getDopeManageByTableNoAndTzxt(tableData.getTableNo().toString(), tzxt, adminId);
        List<DopeManage> list = parseDopeManage(dopeManage);
        //先获取该桌号、投注系统的账户
        if (!CollectionUtils.isEmpty(list)) {
            List<ResultData> resultDataList = new ArrayList<>();
            TableData tableDataNew = tableDataService.getNewestTableData(tableData.getTableNo(), adminId);
            list.stream().forEach(dopeData -> {
                //判断当前时间是否满足投注时间限制且投注桌号一致
                boolean timeResult = checkDopeInfo(tableData.getCreateTime(), dopeData.getTzsjSection1());
                if (timeResult) {
                    ResultData data = new ResultData();
                    data.setCreateTime(tableDataNew.getCreateTime());
                    data.setTableNo(tableDataNew.getTableNo());
                    data.setBattleNo(tableDataNew.getBattleNo());
                    data.setFitNo(tableDataNew.getFitNo() + 1);
                    data.setTzje(dopeData.getTzje().toString());
                    data.setTzxt(tzxt);
                    data.setTzfx(dopeData.getTzfx());
                    data.setTzzh(dopeData.getTzzh());
                    data.setAdminId(adminId);
                    resultDataList.add(data);
                }
            });
            if (CollectionUtils.isNotEmpty(resultDataList)) {
                tableDataService.addResultDataList(resultDataList);
            }
        }
    }

    /**
     * 步骤11-1，取《同桌号数据表》最新一条记录中的“桌号、局号、副号、结果”，
     * 比较在《投注结果表》是否有桌号、局号、副号都相同、且“投注结果”为空的记录。
     *
     * @param tableData
     */
//    public void step11_1(TableData tableData, BigDecimal phxs) {
//        TableData tableDataNew = tableDataService.getNewestTableData(tableData.getTableNo());
//        ResultData resultData = tableDataService.getResultJGNullByTable(tableData.getTableNo(), tableDataNew.getBattleNo(), tableDataNew.getFitNo());
//        if (resultData == null) {
//            //在《投注结果表》中无相应记录。进入步骤12。
//            step12(tableData);
//        } else {
//            //在《投注结果表》中有相应记录，按账号顺序取第1条记录。进入步骤11-2。
//            int result = step11_2(resultData);
//            if (result == 1) {
//                step11_1(tableData, phxs);
//            } else {
//                int tzjg = step11_3(tableDataNew, resultData);
//                BigDecimal yxje = step11_4(tzjg, resultData.getTzje());
//                resultData.setTzjg(tzjg);
//                resultData.setYxje(yxje);
//                step11_5(resultData, phxs);
//                step12(tableData);
//            }
//        }
//    }

    /**
     * 投注结果计算
     */
    public int calcTzResult(String admin) {
        AtomicInteger count = new AtomicInteger(0);
        BdtSystem bdtSystem = tableDataService.getBdtSystem(admin);
        BigDecimal phxs = bdtSystem.getPhxs();
        List<String> idList = userAccountService.getUserIdByAdmin(admin);
        if (CollectionUtils.isNotEmpty(idList)) {
            List<ResultData> list = tableDataService.getResultJGNull(idList);
            if (CollectionUtils.isNotEmpty(list)) {
                list.stream().forEach(x -> {
                    try {
                        TableData tableData = tableDataService.getTableByResult(x.getTableNo(), x.getBattleNo(), x.getFitNo(), x.getCreateTime(), admin);
                        if (tableData != null) {
                            int tzjg = step11_3(tableData, x);
                            BigDecimal yxje = step11_4(tzjg, x.getTzje());
                            x.setTzjg(tzjg);
                            x.setYxje(yxje);
                            step11_5(x, phxs);
                            count.getAndIncrement();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        return count.get();
    }

    /**
     * 步骤12，将《状态表》同桌记录中的状态改为“开牌”。
     */
    public void step12(TableData tableData) {
//        tableDataService.updateStatusByTableNo(tableData.getTableNo(), tableData.getBattleNo(), tableData.getFitNo(), TableStatusEnum.KP.getIndex());
    }

    /**
     * 步骤12-2， 判断在《投注结果表》中所取记录中的“投注状态”。
     *
     * @param resultData
     */
    public int step11_2(ResultData resultData) {
        if (resultData.getTzzt()) {
            //2）投注状态为“成功”。进入步骤11-3。
            return 2;
        } else {
            //1）投注状态为“不成功”。进入步骤11-1。
            return 1;
        }
    }

    /**
     * 步骤12-3，将《同桌号数据表》所取记录中的“结果”与《投注结果表》所取记录中的“投注方向”进行比较，产生“投注结果”。
     *
     * @param tableData
     * @param resultData
     */
    public int step11_3(TableData tableData, ResultData resultData) {
        //同桌表中的结果
        int tableResult = tableData.getResult();
        //投注表结果表中的投注方向
        int tzfx = resultData.getTzfx();
        int tzjg;
        if (tableResult == TableResultEnum.H.getIndex()) {
            tzjg = ResultTzJgEnum.B.getIndex();
        } else if (tableResult == TableResultEnum.Z.getIndex() && tzfx == TableResultEnum.Z.getIndex()) {
            tzjg = ResultTzJgEnum.A.getIndex();
        } else if (tableResult == TableResultEnum.X.getIndex() && tzfx == TableResultEnum.X.getIndex()) {
            tzjg = ResultTzJgEnum.D.getIndex();
        } else {
            tzjg = ResultTzJgEnum.C.getIndex();
        }
        return tzjg;
    }

    /**
     * 步骤11-4，依据“投注结果”、《投注结果表》所取记录中的“投注金额”，计算“有效金额”。
     *
     * @param tzjg
     * @param tzje
     */
    public BigDecimal step11_4(int tzjg, String tzje) {
        BigDecimal yxje;
        BigDecimal tzjgAmount = new BigDecimal(ResultTzJgEnum.getName(tzjg));
        BigDecimal tzjeAmount = new BigDecimal(tzje);
        if (tzjgAmount.compareTo(BigDecimal.ZERO) == 0) {
            yxje = new BigDecimal(0);
        } else {
            yxje = tzjeAmount;
        }
        return yxje;
    }

    /**
     * 步骤11-5，依据“投注结果”、“有效金额”，管理界面、控制子系统中所取参数PHXS，计算“原始输赢”、“实际输赢”。
     *
     * @param resultData
     * @param phxs
     */
    public void step11_5(ResultData resultData, BigDecimal phxs) {
        BigDecimal tzjgAmount = new BigDecimal(ResultTzJgEnum.getName(resultData.getTzjg()));
//        BigDecimal yssy = BigDecimal.valueOf(tzjgAmount * Integer.valueOf(resultData.getTzje()));
        BigDecimal yssy = tzjgAmount.multiply(new BigDecimal(resultData.getTzje()));
        BigDecimal sjsy = yssy.add(phxs.multiply(resultData.getYxje()));
        resultData.setYssy(yssy);
        resultData.setSjsy(sjsy);
        tableDataService.updateResultById(resultData);
        //更新账户有效金额
//        UserAccount userAccount = userAccountService.getUserAccount(resultData.getTzzh());
//        userAccount.setEffectiveAmount(userAccount.getEffectiveAmount().add(resultData.getSjsy()));
//        userAccountService.updateUserAccount(userAccount);
    }

    /**
     * 判断日期是否在某个时间段内
     *
     * @param date        日期
     * @param tzsjSection 时间段  00:00~01:00
     * @return
     */
    private Boolean checkDopeInfo(Date date, String tzsjSection) {
        SimpleDateFormat sf = new SimpleDateFormat("HHmm");
        Integer time = Integer.valueOf(sf.format(date));
        String[] times = tzsjSection.split("~");
        Integer timeStart = Integer.valueOf(times[0].replace(":", ""));
        Integer timeEnd = Integer.valueOf(times[1].replace(":", ""));
        if (time >= timeStart && time < timeEnd) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断日期是否在某个时间段内
     *
     * @param date
     * @param tzsjSection2
     * @return
     */
    private Boolean checkDopeInfo2(Date date, String tzsjSection2) {
        boolean isTrue = false;
        SimpleDateFormat sf = new SimpleDateFormat("mm");
        Integer time = Integer.valueOf(sf.format(date));
        String[] tzsj2 = tzsjSection2.split(",");
        for (int i = 0; i < tzsj2.length; i++) {
            String[] times = tzsj2[i].split("-");
            Integer timeStart = Integer.valueOf(times[0]);
            Integer timeEnd = Integer.valueOf(times[1]);
            if (time >= timeStart && time < timeEnd) {
                isTrue = true;
                return isTrue;
            } else {
                isTrue = false;
            }
        }
        return isTrue;
    }

//    public static void main(String[] args) {
//
//        SimpleDateFormat sf = new SimpleDateFormat("HHmm");
//        Date date = new Date();
//        Integer createTime = Integer.valueOf(sf.format(date));
//
//        String times = "01:00~02:00";
//        String[] time = times.split("~");
//        Integer timeStart = Integer.valueOf(time[0].replace(":", ""));
//        Integer timeEnd = Integer.valueOf(time[1].replace(":", ""));
//        if (createTime >= timeStart && createTime <= timeEnd) {
//            System.out.println("符合");
//        } else {
//            System.out.println("不符合");
//        }
//    }

    //初始状态加入数据库
    @Transactional
    protected void insertCommit(List<TableData> tableDataList, List<StatusData> statusDataList) {
        tableDataService.addTableDataList(tableDataList);
        tableDataService.addStatusDataList(statusDataList);
    }


}

package cn.com.infaith.module.service;

import cn.com.infaith.module.enums.ResultTzJgEnum;
import cn.com.infaith.module.enums.TableNoEnum;
import cn.com.infaith.module.enums.TableResultEnum;
import cn.com.infaith.module.enums.TableStatusEnum;
import cn.com.infaith.module.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BJLDataService {

    public final static List<Integer> tableNoList = Arrays.stream(TableNoEnum.values()).map(TableNoEnum::getIndex).collect(Collectors.toList());

    @Autowired
    private TableDataService tableDataService;

    /**
     * 生成桌子初始数据
     *
     * @return
     */
    public void initTableData() {
        //12桌数据初始化
        List<TableData> tableDataList = new ArrayList<>();
        tableNoList.forEach(no -> {
            TableData tableData = new TableData();
            tableData.setCreateTime(new Date());
            tableData.setTableNo(no);
            tableData.setBattleNo(1);
            tableData.setFitNo(1);
            tableDataList.add(tableData);
        });
        //12桌状态初始化
        List<StatusData> statusDataList = new ArrayList<>();
        tableDataList.forEach(tableData -> {
            StatusData statusData = new StatusData();
            statusData.setTableNo(tableData.getTableNo());
            statusData.setBattleNo(tableData.getBattleNo());
            statusData.setFitNo(tableData.getFitNo());
            statusData.setStatus(TableStatusEnum.NEW.getIndex());
            statusDataList.add(statusData);
        });
        insertCommit(tableDataList, statusDataList);
    }


    /**
     * 步骤1：读取局号、副号、状态，并与《状态表》中同桌记录的局号、副号、状态比较。
     *
     * @param tableNo  当前桌号
     * @param battleNo 当前局号
     * @param fitNo    当前副号
     * @param status   当前状态
     * @return 0：进入步骤15   1：进入"可投注"状态  2：进入"新局准备"  3：进入"开牌"状态
     */
    public int JudgeState(int tableNo, int battleNo, int fitNo, int status) {
        //获取当前桌的最新状态
        StatusData statusData = tableDataService.getStatusByTableNo(tableNo);
        if (statusData == null) {
            initTableData();
            JudgeState(tableNo, battleNo, fitNo, status);
        } else {
            //判断状态
            int state = statusData.getStatus();
            //状态不变。进入步骤15。
            if (state == status) {
                return 0;
            }
            //状态改变，当前状态为“可投注”（局号、副号不会变）。
            if (state != status && status == TableStatusEnum.TZ.getIndex()) {
                return 1;
            }
            //状态改变，当前状态为“新局准备”（局号改变、副号变为1）。
            if (state != status && status == TableStatusEnum.NEW.getIndex()) {
                return 2;
            }
            //状态改变，当前状态为“开牌”（局号、副号不会变）。
            if (state != status && status == TableStatusEnum.KP.getIndex()) {
                return 3;
            }
        }
        return 0;
    }

    /**
     * 可投注状态
     */
    public void tzStatus(int tableNo, int battleNo, int fitNo, int status) {
        //查询《TZ1同桌号下单表》中有无记录。
        int step2Result = step2(tableNo, fitNo, 1);
        if (step2Result == 0) {
            //无记录，进入步骤4
            step4();
        } else if (step2Result == 1) {
            //有记录，当前副号=1。清除表中所有记录。进入步骤4。
            tableDataService.clearAllDopeByTableNoAndTzSystem(tableNo, 1);
            step4();
        } else {
            //有记录，当前副号>1。进入步骤3。
            step3(tableNo);
        }
    }

    /**
     * 新局准备状态
     */
    public void newReady(int tableNo, int battleNo, int fitNo, int status, int tzSystem) {

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
    private int step2(int tableNo, int fitNo, int tzSystem) {
        //查询《TZ1同桌号下单表》中有无记录
        int tzTotal = tableDataService.getDopeCountByTableNo(tableNo, tzSystem);
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
    private int step3(int tableNo) {
        //取表中的第1条记录中的“账号、投注方向、投注金额”进行投注
        DopeData dopeData = tableDataService.getFirstDopeByTableNoAndTzSystem(tableNo, 1);

        return 0;
    }

    /**
     * 步骤4    投注子系统2是否启动。
     *
     * @return
     */
    private int step4() {
        return 0;
    }


    /**
     * 开牌状态
     */
    public void openCard(TableData tableData, BigDecimal phxs, List<DopeData> list) {

        step7(tableData);
        tableData = step8_1(tableData);
        tableData = step8_2(tableData);
        TableMergeData tableMergeData = step8_3(tableData, phxs);
        step8_4(tableMergeData);
        TzSystem tz1System = step9();
        if (tz1System.getStarted()) {
            //投注子系统1启动
            TableData tableDataNew = step10_1(tableData.getTableNo());
            //1）副号<TZ1FH，进入步骤11-1。  2）副号>=TZ1FH，进入步骤10-3。
            int step10_2Result = step10_2(tableDataNew, tz1System);
            if (step10_2Result == 1) {
                step11_1(tableData.getTableNo(), phxs);
            } else {
                //1）ZTSL>TZ1XH，进入步骤11-1。  2）ZTSL<=TZ1XH，进入步骤10-4。
                int step10_3Result = step10_3(tableDataNew, tz1System);
                if (step10_3Result == 1) {
                    step11_1(tableData.getTableNo(), phxs);
                } else {
                    step10_4(tableData, list);
                }
            }
        } else {
            //投注子系统1关闭--进入11-1
            step11_1(tableData.getTableNo(), phxs);
        }

    }

    /**
     * 步骤7    读取牌面数据，包括牌面、结果，并将读取的结果记入《同桌号数据表》
     *
     * @param tableData 牌面数据
     */
    public void step7(TableData tableData) {
        //将读取的结果记入《同桌号数据表》
        int id = tableDataService.addTableData(tableData);
        tableData.setId(id);
    }

    /**
     * 步骤8-1   取《同桌号数据表》最新一条记录（即步骤7生成的记录）中的“副号、牌面”，并将牌面K、Q、J均取为0。
     */
    public TableData step8_1(TableData tableData) {
        //去最新一条记录
        String card = tableData.getCard();
        card = card.replace("K", "0").replace("Q", "0").replace("J", "0");
        tableData.setCard(card);
        return tableData;
    }

    /**
     * 步骤8-2   计算该副XGL、ZGL，XTSL、ZTSL。具体算法见附件。将计算结果记入《同桌号数据表》同条记录中
     *
     * @param tableData
     */
    public TableData step8_2(TableData tableData) {
        tableData.setXgl("");
        tableData.setXtsl("");
        tableData.setZgl("");
        tableData.setZtsl("");
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
    public TableMergeData step8_3(TableData tableData, BigDecimal phxs) {

        String xjz = "0";
        String zjz = "0";
        int result = tableData.getResult();
        if (result == TableResultEnum.Z.getIndex()) {
            xjz = new BigDecimal(-1).add(phxs).setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
            zjz = new BigDecimal(0.95).add(phxs).setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
        } else if (result == TableResultEnum.X.getIndex()) {
            xjz = new BigDecimal(1).add(phxs).setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
            zjz = new BigDecimal(-1).add(phxs).setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
        }
        TableMergeData tableMergeData = new TableMergeData();
        tableMergeData.setCreateTime(tableData.getCreateTime());
        tableMergeData.setTableNo(tableData.getTableNo());
        tableMergeData.setBattleNo(tableData.getBattleNo());
        tableMergeData.setFitNo(tableData.getFitNo());
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
        TableMergeData data = tableDataService.getLastTableMergeDataNotId(tableMergeData.getId());
        if (data == null) {
            tableMergeData.setLjxjz(tableMergeData.getXjz());
            tableMergeData.setLjzjz(tableMergeData.getZjz());
        } else {
            BigDecimal xjz = new BigDecimal(tableMergeData.getXjz());
            BigDecimal zjz = new BigDecimal(tableMergeData.getZjz());
            BigDecimal ljxjz = new BigDecimal(data.getLjxjz());
            BigDecimal ljzjz = new BigDecimal(data.getLjzjz());
            tableMergeData.setLjxjz(xjz.add(ljxjz).setScale(3, BigDecimal.ROUND_DOWN).toPlainString());
            tableMergeData.setLjzjz(zjz.add(ljzjz).setScale(3, BigDecimal.ROUND_DOWN).toPlainString());
        }
        tableDataService.updateTableMergeData(tableMergeData);
    }

    /**
     * 步骤9，投注子系统1是否启动。
     *
     * @return 返回投注系统信息
     */
    public TzSystem step9() {
        TzSystem tzSystem = tableDataService.getTzSystemInfo(1);
        return tzSystem;
    }

    /**
     * 步骤10-1，取《同桌号数据表》最新一条记录中的“副号、ZTSL”，在管理界面、投注子系统1中取参数TZ1FH、TZ1XH。
     */
    public TableData step10_1(int tableNo) {
        TableData tableData = tableDataService.getNewestTableData(tableNo);
        return tableData;
    }

    /**
     * 步骤10-2，比较副号与TZ1FH。
     *
     * @param tableData
     * @param tzSystem
     * @return 1：副号<TZ1FH  2：副号>=TZ1FH
     */
    public int step10_2(TableData tableData, TzSystem tzSystem) {
        if (tableData.getFitNo() < tzSystem.getFh()) {
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
     * @return 1）ZTSL>TZ1XH    2）ZTSL<=TZ1XH
     */
    public int step10_3(TableData tableData, TzSystem tzSystem) {
        BigDecimal ztsl = new BigDecimal(tableData.getZtsl());
        BigDecimal xh = new BigDecimal(tzSystem.getXh());
        int result = ztsl.compareTo(xh);
        if (result == 1) {
            return 1;
        } else {
            return 2;
        }
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
    public void step10_4(TableData tableData, List<DopeData> list) {
        //先获取该桌号、投注系统的账户
        if (!CollectionUtils.isEmpty(list)) {
            List<DopeData> dopeDataList = new ArrayList<>();
            TableData tableDataNew = tableDataService.getNewestTableData(tableData.getTableNo());
            list.stream().forEach(dopeData -> {
                //判断当前时间是否满足投注时间限制且投注桌号一致
                if (tableData.getTableNo().equals(dopeData.getTableNo())) {
                    boolean timeResult = checkDopeInfo(tableData.getCreateTime(), dopeData.getTzsjSection());
                    if (timeResult) {
                        DopeData data = new DopeData();
                        data.setCreateTime(tableDataNew.getCreateTime());
                        data.setTableNo(tableDataNew.getTableNo());
                        data.setBattleNo(tableDataNew.getBattleNo());
                        data.setFitNo(tableDataNew.getFitNo() + 1);
                        data.setTzfx(1);
                        data.setTzje(dopeData.getTzje());
                        data.setTzsjSection(dopeData.getTzsjSection());
                        data.setTzfx(TableResultEnum.Z.getIndex());
                        data.setTzzh(dopeData.getTzzh());
                        dopeDataList.add(data);
                    }
                }
            });
            if (CollectionUtils.isNotEmpty(dopeDataList)) {
                tableDataService.addDopeDataList(dopeDataList);
            }
        }
    }

    /**
     * 步骤11-1，取《同桌号数据表》最新一条记录中的“桌号、局号、副号、结果”，
     * 比较在《投注结果表》是否有桌号、局号、副号都相同、且“投注结果”为空的记录。
     *
     * @param tableNo
     */
    public void step11_1(int tableNo, BigDecimal phxs) {
        TableData tableDataNew = tableDataService.getNewestTableData(tableNo);
        ResultData resultData = tableDataService.getResultJGNullByTable(tableNo, tableDataNew.getBattleNo(), tableDataNew.getFitNo());
        if (resultData == null) {
            //在《投注结果表》中无相应记录。进入步骤xxx。
        } else {
            //在《投注结果表》中有相应记录，按账号顺序取第1条记录。进入步骤11-2。
            int result = step11_2(resultData);
            if (result == 1) {
                step11_1(tableNo, phxs);
            } else {
                int tzjg = step11_3(tableDataNew, resultData);
                BigDecimal yxje = step11_4(tzjg, resultData.getTzje());
                resultData.setTzjg(tzjg);
                resultData.setYxje(yxje);
                step11_5(resultData, phxs);
                step12(tableNo);
            }
        }
    }

    /**
     * 步骤12，将《状态表》同桌记录中的状态改为“开牌”。
     */
    public void step12(int tableNo) {
        tableDataService.updateStatusByTableNo(tableNo, TableStatusEnum.KP.getIndex());
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
        int tzjgAmount = Integer.parseInt(ResultTzJgEnum.getName(tzjg));
        BigDecimal tzjeAmount = new BigDecimal(tzje);
        if (tzjgAmount == 0) {
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
        int tzjgAmount = Integer.parseInt(ResultTzJgEnum.getName(resultData.getTzjg()));
        BigDecimal yssy = BigDecimal.valueOf(tzjgAmount * Integer.valueOf(resultData.getTzje()));
        BigDecimal sjsy = yssy.add(phxs.multiply(resultData.getYxje()));
        resultData.setYssy(yssy);
        resultData.setSjsy(sjsy);
        tableDataService.updateResultById(resultData);
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
        if (time >= timeStart && time <= timeEnd) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        SimpleDateFormat sf = new SimpleDateFormat("HHmm");
        Date date = new Date();
        Integer createTime = Integer.valueOf(sf.format(date));

        String times = "01:00~02:00";
        String[] time = times.split("~");
        Integer timeStart = Integer.valueOf(time[0].replace(":", ""));
        Integer timeEnd = Integer.valueOf(time[1].replace(":", ""));
        if (createTime >= timeStart && createTime <= timeEnd) {
            System.out.println("符合");
        } else {
            System.out.println("不符合");
        }
    }

    //初始状态加入数据库
    @Transactional
    protected void insertCommit(List<TableData> tableDataList, List<StatusData> statusDataList) {
        tableDataService.addTableDataList(tableDataList);
        tableDataService.addStatusDataList(statusDataList);
    }


}

package cn.com.infaith.module.service;

import cn.com.infaith.module.enums.TableNoEnum;
import cn.com.infaith.module.enums.TableStatusEnum;
import cn.com.infaith.module.model.StatusData;
import cn.com.infaith.module.model.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 判断状态
     * @param tableNo   当前桌号
     * @param battleNo  当前局号
     * @param fitNo     当前副号
     * @param status    当前状态
     * @param tzSystem  投注系统
     */
    public void JudgeState(int tableNo, int battleNo, int fitNo, int status, int tzSystem) {
        //获取当前桌的最新状态
        StatusData statusData = tableDataService.getStatusByTableNo(tableNo);
        if (statusData == null) {
            initTableData();
            JudgeState(tableNo, battleNo, fitNo, status, tzSystem);
        } else {
            //判断状态
            int state = statusData.getStatus();
            //状态不变。进入步骤15。
            if (state == status) {
                return;
            }
            //状态改变，当前状态为“可投注”（局号、副号不会变）。
            if (state != status && state == TableStatusEnum.TZ.getIndex()) {
                tzStatus(tableNo, battleNo, fitNo, status, tzSystem);
            }
            //状态改变，当前状态为“新局准备”（局号改变、副号变为1）。
            if (state != status && state == TableStatusEnum.NEW.getIndex()) {
                newReady(tableNo, battleNo, fitNo, status, tzSystem);
            }
            //状态改变，当前状态为“开牌”（局号、副号不会变）。
            if (state != status && state == TableStatusEnum.KP.getIndex()) {
                openCard(tableNo, battleNo, fitNo, status, tzSystem);
            }
        }
    }

    /**
     * 可投注状态
     */
    private void tzStatus(int tableNo, int battleNo, int fitNo, int status, int tzSystem) {

    }

    /**
     * 新局准备状态
     */
    private void newReady(int tableNo, int battleNo, int fitNo, int status, int tzSystem) {

    }

    /**
     * 开牌状态
     */
    private void openCard(int tableNo, int battleNo, int fitNo, int status, int tzSystem) {

    }

















    //初始状态加入数据库
    @Transactional
    protected void insertCommit(List<TableData> tableDataList, List<StatusData> statusDataList) {
        tableDataService.addTableDataList(tableDataList);
        tableDataService.addStatusDataList(statusDataList);
    }


}

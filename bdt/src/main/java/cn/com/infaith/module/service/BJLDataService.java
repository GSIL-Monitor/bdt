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
     * 根据桌号判断状态
     * @param tableNo
     */
    public void JudgeState(int tableNo) {
        //获取当前桌的最新状态
        StatusData statusData = tableDataService.getStatusByTableNo(tableNo);
        if (statusData == null) {
            initTableData();
            JudgeState(tableNo);
        } else {
            //判断状态
            statusData.getStatus();
        }
    }

















    //初始状态加入数据库
    @Transactional
    protected void insertCommit(List<TableData> tableDataList, List<StatusData> statusDataList) {
        tableDataService.addTableDataList(tableDataList);
        tableDataService.addStatusDataList(statusDataList);
    }


}

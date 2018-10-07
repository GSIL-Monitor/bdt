package cn.com.infaith.module.service;

import cn.com.infaith.module.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface TableDataService {

    /**
     * 添加桌面信息
     * @param tableData
     * @return
     */
    int addTableData(TableData tableData);
    Boolean addTableDataList(List<TableData> tableDataList);

    Boolean updateTableData(TableData tableData);

    /**
     * 添加桌子状态
     * @param statusData
     * @return
     */
    Boolean addStatusData(StatusData statusData);
    Boolean addStatusDataList(List<StatusData> statusDataList);

    /**
     * 获取百家乐桌牌情况
     * @return
     */
    List<TableData> getTableInfo();

    /**
     * 通过桌号获取其最新的状态信息
     * @param tableNo
     * @return
     */
    StatusData getStatusByTableNo(int tableNo);

    /**
     * 通过桌号和投注系统获取其是否有数据
     * @param tableNo
     * @param tzSystem
     * @return
     */
    int getDopeCountByTableNo(int tableNo, int tzSystem);

    /**
     * 清楚下单表中所有记录 by 桌号、投注系统
     * @param tableNo
     * @param tzSystem
     * @return
     */
    Boolean clearAllDopeByTableNoAndTzSystem(int tableNo, int tzSystem);

    /**
     * 根据桌号和投注方向取投注下单表的第1条记录中的“账号、投注方向、投注金额”进行投注。
     * @param tableNo
     * @param tzSystem
     * @return
     */
    DopeData getFirstDopeByTableNoAndTzSystem(int tableNo, int tzSystem);

    /**
     * 根据桌号获取最新一条信息
     * @param tableNo
     * @return
     */
    TableData getNewestTableData(int tableNo);

    /**
     * 添加合并数据
     * @param tableMergeData
     * @return  主键id
     */
    int addTableMergeData(TableMergeData tableMergeData);

    /**
     * 获取除去当前id的最新一条合并数据
     * @param id
     * @return
     */
    TableMergeData getLastTableMergeDataNotId(int id);

    /**
     * 更新合并数据
     * @param tableMergeData
     * @return
     */
    Boolean updateTableMergeData(TableMergeData tableMergeData);

    /**
     * 投注系统开关
     * @param started
     * @param tzxt
     * @param fh
     * @param xh
     * @return
     */
    Boolean updateTzStartOrClose(Boolean started, int tzxt, int fh, String xh);

    TzSystem getTzSystemInfo(int tzxt);

    /**
     * 获取当前桌号以及投注系统下的投注信息
     * @param tableNo
     * @param tzSystem
     * @return
     */
    List<DopeData> getDopeByTableNoAndTzSystem(int tableNo, int tzSystem);

    /**
     * 披露添加下单投注数据
     * @param list
     * @return
     */
    Boolean addDopeDataList(List<DopeData> list);

    /**
     * 通过桌号、局号、副号查询投注结果表里为结果为空的信息
     * @param tableNo
     * @param battleNo
     * @param fitNo
     * @return
     */
    ResultData getResultJGNullByTable(int tableNo, int battleNo, int fitNo);

    /**
     * 更新“投注结果”、“有效金额”、“原始输赢”、“实际输赢"   投注结果表
     * @param record
     * @return
     */
    int updateResultById(ResultData record);

    /**
     * 更新状态表
     * @param tableNo
     * @param status
     * @param battleNo
     * @param fitNo
     * @return
     */
    Boolean updateStatusByTableNo(int tableNo, int battleNo, int fitNo, int status);

    /**
     * 通过日期，桌号，句号查询桌面信息
     * @param createTime
     * @param tableNo
     * @param battleNo
     * @return
     */
    List<TableData> searchTableData(Long createTime, Integer tableNo, Integer battleNo);

    /**
     * 获取投注结果信息
     * @param createTime
     * @param tzxt
     * @param tzzh
     * @return
     */
    List<ResultData> searchResultData(Long createTime, Integer tzxt, String tzzh);

    /**
     * bdt系统开关
     * @param started
     * @param ps
     * @param phxs
     * @return
     */
    Boolean bdtSystemStarted(Boolean started, Integer ps, BigDecimal phxs);

    BdtSystem getBdtSystem();

    Boolean addResultData(ResultData resultData);

    /**
     * 通过id删除下单信息
     * @param id
     * @return
     */
    Boolean deleteDopeDataById(int id);

    /**
     * 添加自动投注信息
     * @param list
     * @return
     */
    Boolean addDopeManage(List<DopeManage> list);

    /**
     * 获取自动投注信息
     * @return
     */
    List<DopeManage> getDopeMangeList(int tzxt);

    /**
     * 通过投注账号获取id
     * @param tzzh
     * @return
     */
    Integer getDopeManageIdByTzzh(String tzzh);

    Boolean addDopeManage(DopeManage dopeManage);

    Boolean updateDopeManage(DopeManage dopeManage);


}

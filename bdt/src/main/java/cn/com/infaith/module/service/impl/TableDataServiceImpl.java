package cn.com.infaith.module.service.impl;

import cn.com.infaith.module.mapper.*;
import cn.com.infaith.module.model.*;
import cn.com.infaith.module.service.TableDataService;
import cn.com.infaith.module.util.ResponseJsonUtil;
import cn.com.infaith.module.util.TimeUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TableDataServiceImpl implements TableDataService {

    @Autowired
    private TableDataMapper tableDataMapper;
    @Autowired
    private StatusDataMapper statusDataMapper;
    @Autowired
    private DopeDataMapper dopeDataMapper;
    @Autowired
    private TableMergeDataMapper tableMergeDataMapper;
    @Autowired
    private TzSystemMapper tzSystemMapper;
    @Autowired
    private ResultDataMapper resultDataMapper;
    @Autowired
    private BdtSystemMapper bdtSystemMapper;
    @Autowired
    private DopeManageMapper dopeManageMapper;

    @Override
    public Integer addTableData(TableData tableData) {

        tableData.setCreateTime(new Date(tableData.getCreateDate()));
        tableData.setCreated(TimeUtil.getDateZeroDate(new Date(tableData.getCreateDate())));
        int count = tableDataMapper.checkIsHaveTableData(tableData.getCreated(), tableData.getTableNo(), tableData.getBattleNo(),
                tableData.getFitNo(), tableData.getCard());
        if (count > 0) {
            return null;
        }
        return tableDataMapper.insert(tableData);
    }

    @Override
    public Boolean addTableDataList(List<TableData> tableDataList) {
        List<TableData> list = new ArrayList<>();
        for (int i = 0; i < tableDataList.size(); i++) {
            TableData tableData = tableDataList.get(i);
            int count = tableDataMapper.checkIsHaveTableData(tableData.getCreated(), tableData.getTableNo(), tableData.getBattleNo(),
                    tableData.getFitNo(), tableData.getCard());
            if (count > 0) {
                continue;
            }
            tableData.setCreateTime(new Date(tableData.getCreateDate()));
            tableData.setCreated(new Date(tableData.getCreateDate()));
            list.add(tableData);
        }
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return tableDataMapper.addTableDataList(list) == list.size() ? true : false;
    }

    @Override
    public Boolean updateTableData(TableData tableData) {

        tableData.setCreateTime(new Date(tableData.getCreateDate()));
        tableData.setCreated(tableData.getCreateTime());
        return tableDataMapper.updateByPrimaryKey(tableData) > 0 ? true : false;
    }

    @Override
    public Boolean addStatusData(StatusData statusData) {
        return statusDataMapper.insert(statusData) > 0 ? true : false;
    }

    @Override
    public Boolean addStatusDataList(List<StatusData> statusDataList) {
        return statusDataMapper.addStatusDataList(statusDataList) == statusDataList.size() ? true : false;
    }

    @Override
    public List<TableData> getTableInfo() {
        return tableDataMapper.getTableInfo();
    }

    @Override
    public StatusData getStatusByTableNo(int tableNo) {
        return statusDataMapper.getStatusByTableNo(tableNo);
    }

    @Override
    public int getDopeCountByTableNo(int tableNo, int tzSystem) {
        return dopeDataMapper.getDopeCountByTableNo(tableNo, tzSystem);
    }

    @Override
    public Boolean clearAllDopeByTableNoAndTzSystem(int tableNo, int tzSystem) {
        return dopeDataMapper.clearAllDopeByTableNoAndTzSystem(tableNo, tzSystem);
    }

    @Override
    public DopeData getFirstDopeByTableNoAndTzSystem(int tableNo, int tzSystem) {
        return dopeDataMapper.getFirstDopeByTableNoAndTzSystem(tableNo, tzSystem);
    }

    @Override
    public DopeData getFirstDopeByTableNoAndTzSystemOrderByAccount(int tableNo, int tzSystem) {
        return dopeDataMapper.getFirstDopeByTableNoAndTzSystemOrderByAccount(tableNo, tzSystem);
    }

    @Override
    public TableData getNewestTableData(int tableNo) {
        return tableDataMapper.getNewestTableData(tableNo);
    }

    @Override
    public int addTableMergeData(TableMergeData tableMergeData) {
        return tableMergeDataMapper.insert(tableMergeData);
    }

    @Override
    public TableMergeData getLastTableMergeDataNotId(int id) {
        return tableMergeDataMapper.getLastTableMergeDataNotId(id);
    }

    @Override
    public Boolean updateTableMergeData(TableMergeData tableMergeData) {
        return tableMergeDataMapper.updateByPrimaryKey(tableMergeData) > 0 ? true : false;
    }

    @Override
    public Boolean updateTzStartOrClose(Boolean started, int tzxt, int fh, String xh) {
        return tzSystemMapper.updateStartOrClose(started, tzxt, fh, xh) > 0 ? true : false;
    }

    @Override
    public TzSystem getTzSystemInfo(int tzxt) {
        return tzSystemMapper.getTzSystemInfo(tzxt);
    }

    @Override
    public List<DopeData> getDopeByTableNoAndTzSystem(int tableNo, int tzSystem) {
        return dopeDataMapper.getDopeByTableNoAndTzSystem(tableNo, tzSystem);
    }

    @Override
    public Boolean addDopeDataList(List<DopeData> list) {
        return dopeDataMapper.addDopeDataList(list) > 0 ? true : false;
    }

    @Override
    public ResultData getResultJGNullByTable(int tableNo, int battleNo, int fitNo) {
        return resultDataMapper.getResultJGNullByTable(tableNo, battleNo, fitNo);
    }

    @Override
    public int updateResultById(ResultData record) {
        return resultDataMapper.updateById(record);
    }

    @Override
    public Boolean updateStatusByTableNo(int tableNo, int battleNo, int fitNo, int status) {
        return statusDataMapper.updateStatusByTableNo(tableNo, status, battleNo, fitNo) > 0 ? true : false;
    }

    @Override
    public JSONObject searchTableData(Long createTime, Integer tableNo, Integer battleNo, Integer pageNum, Integer pageSize) {

        Date createDate;
        if (createTime == null || createTime == 0) {
            createDate = null;
        } else {
            createDate = new Date(createTime);
        }
        Page<TableData> page = PageHelper.startPage(pageNum, pageSize, true);
        List<TableData> list = tableDataMapper.searchTableData(createDate, tableNo, battleNo);
        return ResponseJsonUtil.getResponseJson(200,"SUCCESS",list,pageNum,pageSize,page.getTotal());
    }

    @Override
    public JSONObject searchResultData(Long createTime, Integer tzxt, String tzzh, Integer pageNum, Integer pageSize) {

        Date createDate;
        if (createTime == null || createTime == 0) {
            createDate = null;
        } else {
            createDate = new Date(createTime);
        }
        Page<ResultData> page = PageHelper.startPage(pageNum, pageSize, true);
        List<ResultData> list = resultDataMapper.searchResultData(createDate, tzxt, tzzh);
        return ResponseJsonUtil.getResponseJson(200,"SUCCESS",list,pageNum,pageSize,page.getTotal());
    }

    @Override
    public Boolean bdtSystemStarted(Boolean started, Integer ps, BigDecimal phxs) {
        return bdtSystemMapper.bdtSystemStarted(started, ps, phxs);
    }

    @Override
    public BdtSystem getBdtSystem() {
        return bdtSystemMapper.getBdtSystem();
    }

    @Override
    public Boolean addResultData(ResultData resultData) {
        return resultDataMapper.insert(resultData) > 0 ? true : false;
    }

    @Override
    public Boolean deleteDopeDataById(int id) {
        return dopeDataMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    @Override
    public Boolean addDopeManage(List<DopeManage> list) {

        if (CollectionUtils.isNotEmpty(list)) {
            int count = dopeManageMapper.insertList(list);
            if (count == list.size()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<DopeManage> getDopeMangeList(int tzxt) {
        return dopeManageMapper.getDopeMangeList(tzxt);
    }

    @Override
    public Integer getDopeManageIdByTzzh(String tzzh) {
        return dopeManageMapper.getDopeManageIdByTzzh(tzzh);
    }

    @Override
    public List<DopeManage> getDopeManageByTableNoAndTzxt(String tableNo, int tzxt) {
        return dopeManageMapper.getDopeManageByTableNoAndTzxt(tableNo, tzxt);
    }

    @Override
    public Boolean addDopeManage(DopeManage dopeManage) {
        return dopeManageMapper.insert(dopeManage) > 0 ? true : false;
    }

    @Override
    public Boolean updateDopeManage(DopeManage dopeManage) {
        return dopeManageMapper.updateByPrimaryKey(dopeManage) > 0 ? true : false;
    }

    @Override
    public List<Map<Integer, String>> getLJXJZ(Long startTime, Long endTime) {

        Date startDate;
        if (startTime == null || startTime == 0) {
            startDate = TimeUtil.getTodayZeroDate();
        } else {
            startDate = new Date(startTime);
        }
        Date endDate;
        if (endTime == null || endTime == 0) {
            endDate = TimeUtil.dateAddDays(TimeUtil.getTodayZeroDate(), 1);
        } else {
            endDate = new Date(endTime);
        }
        List<Map<Integer, String>> map = tableMergeDataMapper.getResultInfo(startDate, endDate);
        return map;
    }

    @Override
    public List<Map<Integer, String>> getLJZJZ(Long startTime, Long endTime) {
        Date startDate;
        if (startTime == null || startTime == 0) {
            startDate = TimeUtil.getTodayZeroDate();
        } else {
            startDate = new Date(startTime);
        }
        Date endDate;
        if (endTime == null || endTime == 0) {
            endDate = TimeUtil.dateAddDays(TimeUtil.getTodayZeroDate(), 1);
        } else {
            endDate = new Date(endTime);
        }
        return tableMergeDataMapper.getLJZJZ(startDate, endDate);
    }

    @Override
    public Boolean updateStatus(StatusData statusData) {
        return statusDataMapper.updateByPrimaryKey(statusData) > 0 ? true : false;
    }

    @Override
    public int getCountFirstFitByTable(int tableNo, int battleNo) {
        return tableDataMapper.getCountFirstFitByTable(tableNo, battleNo);
    }

    @Override
    public List<ResultData> getNeedTzDataList(Integer tableNo) {
        return resultDataMapper.getNeedTzDataList(tableNo);
    }

    @Override
    public int updateTzztList(List<ResultData> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(x -> {
                resultDataMapper.updateTzzt(x);
            });
            return 1;
        }
        return 0;
    }


}

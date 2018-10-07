package cn.com.infaith.module.service.impl;

import cn.com.infaith.module.mapper.*;
import cn.com.infaith.module.model.*;
import cn.com.infaith.module.service.TableDataService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public int addTableData(TableData tableData) {

        tableData.setCreateTime(new Date(tableData.getCreateDate()));
        return tableDataMapper.insert(tableData);
    }

    @Override
    public Boolean addTableDataList(List<TableData> tableDataList) {

        tableDataList.forEach(x -> {
            x.setCreateTime(new Date(x.getCreateDate()));
        });
        return tableDataMapper.addTableDataList(tableDataList) == tableDataList.size() ? true : false;
    }

    @Override
    public Boolean updateTableData(TableData tableData) {
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
    public List<TableData> searchTableData(Long createTime, Integer tableNo, Integer battleNo) {

        Date createDate;
        if (createTime == null || createTime == 0) {
            createDate = null;
        } else {
            createDate = new Date(createTime);
        }
        return tableDataMapper.searchTableData(createDate, tableNo, battleNo);
    }

    @Override
    public List<ResultData> searchResultData(Long createTime, Integer tzxt, String tzzh) {

        Date createDate;
        if (createTime == null || createTime == 0) {
            createDate = null;
        } else {
            createDate = new Date(createTime);
        }
        return resultDataMapper.searchResultData(createDate, tzxt, tzzh);
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
    public Boolean addDopeManage(DopeManage dopeManage) {
        return dopeManageMapper.insert(dopeManage) > 0 ? true : false;
    }

    @Override
    public Boolean updateDopeManage(DopeManage dopeManage) {
        return dopeManageMapper.updateByPrimaryKey(dopeManage) > 0 ? true : false;
    }


}

package cn.com.infaith.module.service.impl;

import cn.com.infaith.module.mapper.StatusDataMapper;
import cn.com.infaith.module.mapper.TableDataMapper;
import cn.com.infaith.module.model.StatusData;
import cn.com.infaith.module.model.TableData;
import cn.com.infaith.module.service.TableDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TableDataServiceImpl implements TableDataService {

    @Autowired
    private TableDataMapper tableDataMapper;
    @Autowired
    private StatusDataMapper statusDataMapper;

    @Override
    public int addTableData(TableData tableData) {

        tableData.setCreateTime(new Date(tableData.getCreateDate()));
        return tableDataMapper.insert(tableData);
    }

    @Override
    public Boolean addTableDataList(List<TableData> tableDataList) {
        return tableDataMapper.addTableDataList(tableDataList) == tableDataList.size() ? true : false;
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
}

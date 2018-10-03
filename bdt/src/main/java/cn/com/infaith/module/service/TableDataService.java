package cn.com.infaith.module.service;

import cn.com.infaith.module.model.StatusData;
import cn.com.infaith.module.model.TableData;
import org.springframework.stereotype.Service;

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
}

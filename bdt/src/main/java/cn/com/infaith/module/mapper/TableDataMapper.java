package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TableData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TableDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TableData record);

    TableData selectByPrimaryKey(Integer id);

    List<TableData> selectAll();

    int updateByPrimaryKey(TableData record);

    int addTableDataList(@Param("list") List<TableData> tableDataList);

    List<TableData> getTableInfo();

    TableData getNewestTableData(int tableNo);

    List<TableData> searchTableData(@Param("createTime") Date createTime, @Param("tableNo") Integer tableNo, @Param("battleNo") Integer battleNo);

    int checkIsHaveTableData(@Param("created") Date created, @Param("tableNo") Integer tableNo,
                             @Param("battleNo") Integer battleNo, @Param("fitNo") Integer fitNo,
                             @Param("card") String card);

    int getCountFirstFitByTable(@Param("tableNo") int tableNo, @Param("battleNo") int battleNo);

    String getCardTable(@Param("tableNo") int tableNo,@Param("battleNo") int battleNo, @Param("fitNo") int fitNo);
}
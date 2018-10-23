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

    TableData getNewestTableData(@Param("tableNo") int tableNo, @Param("adminId") String adminId);

    List<TableData> searchTableData(@Param("createTime") Date createTime, @Param("tableNo") Integer tableNo, @Param("battleNo") Integer battleNo,
                                    @Param("adminId") String adminId);

    int checkIsHaveTableData(@Param("createTime") Date created, @Param("tableNo") Integer tableNo,
                             @Param("battleNo") Integer battleNo, @Param("fitNo") Integer fitNo,
                             @Param("card") String card, @Param("adminId") String adminId);

    int getCountFirstFitByTable(@Param("tableNo") int tableNo, @Param("battleNo") int battleNo, @Param("adminId") String adminId);

    String getCardTable(@Param("tableNo") int tableNo,@Param("battleNo") int battleNo, @Param("fitNo") int fitNo,
                        @Param("adminId") String adminId);

    TableData getTableByResult(@Param("tableNo") int tableNo,@Param("battleNo") int battleNo,
                               @Param("fitNo") int fitNo, @Param("resultDate") Date resultDate,
                               @Param("adminId") String adminId);

    TableData getFitOneTable(@Param("tableNo") int tableNo, @Param("battleNo") int battleNo, @Param("adminId") String adminId);

}
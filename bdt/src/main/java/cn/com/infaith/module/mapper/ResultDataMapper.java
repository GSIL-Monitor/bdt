package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.ResultData;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ResultDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResultData record);

    ResultData selectByPrimaryKey(Integer id);

    List<ResultData> selectAll();

    int updateById(ResultData record);

    ResultData getResultJGNullByTable(@Param("tableNo") int tableNo, @Param("battleNo") int battleNo, @Param("fitNo") int fitNo);

    List<ResultData> searchResultData(@Param("createTime") Date createTime, @Param("tzxt") Integer tzxt, @Param("tzzh") String tzzh);

    List<ResultData> getNeedTzDataList(@Param("tableNo") Integer tableNo);

    int updateTzzt(ResultData record);

    BigDecimal getAllYxje(@Param("createTime") Date createTime, @Param("tzxt") Integer tzxt, @Param("tzzh") String tzzh);
    BigDecimal getAllYssy(@Param("createTime") Date createTime, @Param("tzxt") Integer tzxt, @Param("tzzh") String tzzh);
    BigDecimal getAllSjsy(@Param("createTime") Date createTime, @Param("tzxt") Integer tzxt, @Param("tzzh") String tzzh);
}
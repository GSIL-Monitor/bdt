package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.ResultData;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ResultDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResultData record);

    int insertList(@Param("list") List<ResultData> list);

    ResultData selectByPrimaryKey(Integer id);

    List<ResultData> selectAll();

    int updateById(ResultData record);

    ResultData getResultJGNullByTable(@Param("tableNo") int tableNo, @Param("battleNo") int battleNo, @Param("fitNo") int fitNo);

    List<ResultData> getResultJGNull(List<String> list);

    List<ResultData> searchResultData(@Param("createTime") Date createTime, @Param("tzxt") Integer tzxt, @Param("tzzh") String tzzh,
                                      @Param("adminId") String adminId);

    List<ResultData> getNeedTzDataList(@Param("tableNo") Integer tableNo, @Param("tzzh") String tzzh);

    int updateTzzt(ResultData record);

    BigDecimal getAllYxje(@Param("createTime") Date createTime, @Param("tzxt") Integer tzxt, @Param("tzzh") String tzzh, @Param("adminId") String adminId);
    BigDecimal getAllYssy(@Param("createTime") Date createTime, @Param("tzxt") Integer tzxt, @Param("tzzh") String tzzh, @Param("adminId") String adminId);
    BigDecimal getAllSjsy(@Param("createTime") Date createTime, @Param("tzxt") Integer tzxt, @Param("tzzh") String tzzh, @Param("adminId") String adminId);

    int updateResult(@Param("tableNo") int tableNo, @Param("battleNo") int battleNo, @Param("fitNo") int fitNo);

    int getAllResultCount(@Param("tzzt") int tzzt, @Param("userIdList") List<String> userIdList, @Param("tzxt") int tzxt);
}
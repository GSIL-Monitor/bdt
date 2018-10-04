package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.ResultData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResultDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResultData record);

    ResultData selectByPrimaryKey(Integer id);

    List<ResultData> selectAll();

    int updateById(ResultData record);

    ResultData getResultJGNullByTable(@Param("tableNo") int tableNo, @Param("battleNo") int battleNo, @Param("fitNo") int fitNo);
}
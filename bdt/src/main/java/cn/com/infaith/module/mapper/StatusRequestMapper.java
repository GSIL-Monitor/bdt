package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.StatusRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatusRequestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StatusRequest record);

    StatusRequest selectByPrimaryKey(Integer id);

    List<StatusRequest> selectAll();

    int updateByPrimaryKey(StatusRequest record);

    int selectByTable(@Param("tableNo") int tableNo, @Param("battleNo") int battleNo,
                      @Param("fitNo") int fitNo, @Param("status") int status);

    StatusRequest getLastStatusRequest(@Param("id") Integer id, @Param("tableNo") int tableNo);
}
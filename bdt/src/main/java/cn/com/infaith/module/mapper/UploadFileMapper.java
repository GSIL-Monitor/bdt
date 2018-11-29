package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.UploadFile;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UploadFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UploadFile record);

    UploadFile selectByPrimaryKey(Integer id);

    List<UploadFile> selectAll(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("type") Integer type);

    int updateByPrimaryKey(UploadFile record);

    List<UploadFile> selectByIds(@Param("list") List<Integer> list);
}
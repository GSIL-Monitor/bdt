package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.UploadFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UploadFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UploadFile record);

    UploadFile selectByPrimaryKey(Integer id);

    List<UploadFile> selectAll();

    int updateByPrimaryKey(UploadFile record);

    List<UploadFile> selectByIds(@Param("list") List<String> list);
}
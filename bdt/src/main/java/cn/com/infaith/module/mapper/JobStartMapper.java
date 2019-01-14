package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.JobStart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobStartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobStart record);

    JobStart selectByPrimaryKey(Integer id);

    List<JobStart> selectAll();

    int updateByPrimaryKey(@Param("jobStarted") Boolean jobStarted);
}
package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.WxSendRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxSendRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxSendRecord record);

    WxSendRecord selectByPrimaryKey(Integer id);

    List<WxSendRecord> selectAll();

    int updateByPrimaryKey(WxSendRecord record);

    Integer selectByType(Integer type);
}
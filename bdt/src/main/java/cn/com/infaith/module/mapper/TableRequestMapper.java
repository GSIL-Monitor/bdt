package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TableRequest;
import java.util.List;

public interface TableRequestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TableRequest record);

    TableRequest selectByPrimaryKey(Integer id);

    List<TableRequest> selectAll();

    int updateByPrimaryKey(TableRequest record);
}
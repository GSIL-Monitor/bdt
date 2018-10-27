package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface TableInfoMapper {

    int insert(TableInfo tableInfo);

    int checkIsHaveTableData(@Param("createTime") Date createTime, @Param("tableNo") int tableNo,
                             @Param("battleNo") int battleNo, @Param("fitNo") int fitNo, @Param("card") String card);
}

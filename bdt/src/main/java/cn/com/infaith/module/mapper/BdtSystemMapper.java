package cn.com.infaith.module.mapper;

import cn.com.infaith.module.model.BdtSystem;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BdtSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BdtSystem record);

    BdtSystem selectByPrimaryKey(Integer id);

    List<BdtSystem> selectAll();

    int updateByPrimaryKey(BdtSystem record);

    Boolean bdtSystemStarted(@Param("started") Boolean started, @Param("ps") Integer ps,
                             @Param("phxs") BigDecimal phxs, @Param("adminId") String adminId,
                             @Param("txxs") BigDecimal txxs);

    BdtSystem getBdtSystem(String adminId);
}
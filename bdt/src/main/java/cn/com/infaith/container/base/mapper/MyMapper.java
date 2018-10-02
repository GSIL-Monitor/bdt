package cn.com.infaith.container.base.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by Administrator on 2017/5/26.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

package com.passcloud.common.core.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * The interface My mapper.
 *
 * @param <T> the type parameter liyuzhang
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

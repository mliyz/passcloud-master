package com.paascloud.provider.mapper;

import com.paascloud.provider.model.domain.TpcJobTask;
import com.passcloud.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The interface Tpc job task mapper.
 *
 * @author liyuzhang
 */
@Mapper
@Component
public interface TpcJobTaskMapper extends MyMapper<TpcJobTask> {
}
package com.paascloud.provider.mapper;

import com.paascloud.provider.model.domain.TpcMqSubscribeTag;
import com.passcloud.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The interface Tpc mq subscribe tag mapper.
 *
 * @author liyuzhang
 */
@Mapper
@Component
public interface TpcMqSubscribeTagMapper extends MyMapper<TpcMqSubscribeTag> {
}
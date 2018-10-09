package com.paascloud.provider.mapper;

import com.paascloud.provider.model.domain.UacGroup;
import com.passcloud.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The interface Uac group mapper.
 *
 * @author liyuzhang
 */
@Mapper
@Component
public interface UacGroupMapper extends MyMapper<UacGroup> {
}
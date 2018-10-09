package com.paascloud.provider.mapper;

import com.paascloud.provider.model.domain.PtcPayInfo;
import com.passcloud.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The interface Ptc pay info mapper.
 *
 * @author liyuzhang
 */
@Mapper
@Component
public interface PtcPayInfoMapper extends MyMapper<PtcPayInfo> {
}
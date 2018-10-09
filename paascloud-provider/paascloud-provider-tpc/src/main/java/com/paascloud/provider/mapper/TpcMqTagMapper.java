package com.paascloud.provider.mapper;

import com.paascloud.provider.model.domain.TpcMqTag;
import com.paascloud.provider.model.vo.TpcMqTagVo;
import com.passcloud.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Tpc mq tag mapper.
 *
 * @author liyuzhang
 */
@Mapper
@Component
public interface TpcMqTagMapper extends MyMapper<TpcMqTag> {
	/**
	 * List tpc mq tag vo with page list.
	 *
	 * @param tpcMqTag the tpc mq tag
	 *
	 * @return the list
	 */
	List<TpcMqTagVo> listTpcMqTagVoWithPage(TpcMqTag tpcMqTag);
}
package com.paascloud.provider.service;

import com.paascloud.provider.model.domain.TpcMqTag;
import com.paascloud.provider.model.vo.TpcMqTagVo;
import com.passcloud.common.core.support.IService;

import java.util.List;

/**
 * The interface Tpc mq tag service.
 *
 * @author liyuzhang
 */
public interface TpcMqTagService extends IService<TpcMqTag> {
	/**
	 * 查询Tag列表.
	 *
	 * @param mdcMqTag the mdc mq tag
	 *
	 * @return the list
	 */
	List<TpcMqTagVo> listWithPage(TpcMqTag mdcMqTag);

	/**
	 * 根据ID删除TAG.
	 *
	 * @param tagId the tag id
	 *
	 * @return the int
	 */
	int deleteTagById(Long tagId);
}

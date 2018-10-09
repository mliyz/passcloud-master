package com.paascloud.provider.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * The class Mdc mq producer vo.
 *
 * @author liyuzhang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TpcMqProducerVo extends TpcMqPublishVo {
	private static final long serialVersionUID = -5644698735373761104L;

	/**
	 * 发布Topic集合
	 */
	private List<TpcMqTopicVo> mqTopicVoList;

}

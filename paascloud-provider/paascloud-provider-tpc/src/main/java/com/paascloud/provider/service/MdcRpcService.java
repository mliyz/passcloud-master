package com.paascloud.provider.service;


import com.github.pagehelper.PageInfo;
import com.paascloud.provider.exceptions.TpcBizException;
import com.passcloud.common.base.dto.MessageQueryDto;
import com.passcloud.common.base.dto.MqMessageVo;
import com.passcloud.common.base.enums.ErrorCodeEnum;
import com.passcloud.common.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * The class Mdc rpc service.
 *
 * @author liyuzhang
 */
@Slf4j
@Service
public class MdcRpcService {
	@Resource
	private MdcMqMessageFeignApi mdcMqMessageFeignApi;

	public Wrapper<PageInfo<MqMessageVo>> queryMessageListWithPage(final MessageQueryDto messageQueryDto) {
		Wrapper<PageInfo<MqMessageVo>> wrapper = mdcMqMessageFeignApi.queryMessageListWithPage(messageQueryDto);
		if (wrapper == null) {
			log.error("查询消息记录. 失败 result is null");
			throw new TpcBizException(ErrorCodeEnum.GL99990002);
		}
		return wrapper;
	}
}
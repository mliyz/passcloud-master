package com.paascloud.provider.service.hystrix;


import com.github.pagehelper.PageInfo;
import com.paascloud.provider.service.OpcMqMessageFeignApi;
import com.passcloud.common.base.dto.MessageQueryDto;
import com.passcloud.common.base.dto.MqMessageVo;
import com.passcloud.common.util.wrapper.Wrapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The class Opc mq message feign api hystrix.
 *
 * @author liyuzhang
 */
@Component
public class OpcMqMessageFeignApiHystrix implements OpcMqMessageFeignApi {

	@Override
	public Wrapper<List<String>> queryMessageKeyList(final List<String> messageKeyList) {
		return null;
	}

	@Override
	public Wrapper<PageInfo<MqMessageVo>> queryMessageListWithPage(final MessageQueryDto messageQueryDto) {
		return null;
	}
}

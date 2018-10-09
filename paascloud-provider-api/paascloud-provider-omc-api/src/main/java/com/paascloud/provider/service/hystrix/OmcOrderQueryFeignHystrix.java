package com.paascloud.provider.service.hystrix;

import com.paascloud.provider.model.dto.OrderDto;
import com.paascloud.provider.service.OmcOrderQueryFeignApi;
import com.passcloud.common.util.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * The class Omc order query feign hystrix.
 *
 * @author liyuzhang
 */
@Component
public class OmcOrderQueryFeignHystrix implements OmcOrderQueryFeignApi {


	@Override
	public Wrapper<OrderDto> queryByOrderNo(final String orderNo) {
		return null;
	}

	@Override
	public Wrapper<OrderDto> queryByUserIdAndOrderNo(final Long userId, final String orderNo) {
		return null;
	}
}

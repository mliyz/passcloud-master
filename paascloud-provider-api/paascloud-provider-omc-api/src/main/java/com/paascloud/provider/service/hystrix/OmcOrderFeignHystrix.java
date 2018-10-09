package com.paascloud.provider.service.hystrix;

import com.paascloud.provider.model.dto.OrderDto;
import com.paascloud.provider.service.OmcOrderFeignApi;
import com.passcloud.common.util.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * The class Omc order feign hystrix.
 *
 * @author liyuzhang
 */
@Component
public class OmcOrderFeignHystrix implements OmcOrderFeignApi {

	@Override
	public Wrapper updateOrderById(final OrderDto order) {
		return null;
	}
}

package com.paascloud.provider.service.hystrix;

import com.paascloud.provider.model.dto.ProductDto;
import com.paascloud.provider.service.MdcProductFeignApi;
import com.passcloud.common.util.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * The class Mdc product feign hystrix.
 *
 * @author liyuzhang
 */
@Component
public class MdcProductFeignHystrix implements MdcProductFeignApi {

	@Override
	public Wrapper<Integer> updateProductStockById(final ProductDto productDto) {
		return null;
	}

	@Override
	public Wrapper<String> getMainImage(final Long productId) {
		return null;
	}
}

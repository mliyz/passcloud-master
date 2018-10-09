package com.paascloud.provider.service.hystrix;

import com.github.pagehelper.PageInfo;
import com.paascloud.provider.model.dto.ProductCategoryDto;
import com.paascloud.provider.model.dto.ProductReqDto;
import com.paascloud.provider.service.MdcProductCategoryQueryFeignApi;
import com.passcloud.common.util.wrapper.Wrapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The class Mdc product category query feign hystrix.
 *
 * @author liyuzhang
 */
@Component
public class MdcProductCategoryQueryFeignHystrix implements MdcProductCategoryQueryFeignApi {
	@Override
	public Wrapper<List<ProductCategoryDto>> getProductCategoryData(final Long pid) {
		return null;
	}

	@Override
	public Wrapper<PageInfo> getProductList(final ProductReqDto productReqDto) {
		return null;
	}
}

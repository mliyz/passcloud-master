package com.paascloud.provider.web.rpc;

import com.google.common.base.Preconditions;
import com.paascloud.provider.model.dto.ProductDto;
import com.paascloud.provider.service.MdcProductFeignApi;
import com.paascloud.provider.service.MdcProductService;
import com.passcloud.common.base.enums.ErrorCodeEnum;
import com.passcloud.common.core.support.BaseController;
import com.passcloud.common.util.PubUtils;
import com.passcloud.common.util.wrapper.WrapMapper;
import com.passcloud.common.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * The class Mdc product feign client.
 *
 * @author liyuzhang
 */
@RefreshScope
@RestController
@Api(value = "API - UacUserQueryFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdcProductFeignClient extends BaseController implements MdcProductFeignApi {
	@Resource
	private MdcProductService mdcProductService;

	@Override
	@ApiOperation(httpMethod = "POST", value = "更新商品库存")
	public Wrapper<Integer> updateProductStockById(@RequestBody ProductDto productDto) {
		logger.info("更新商品库存. productDto={}", productDto);
		Preconditions.checkArgument(!PubUtils.isNull(productDto, productDto.getId()), ErrorCodeEnum.MDC10021021.msg());
		int result = mdcProductService.updateProductStockById(productDto);
		return WrapMapper.ok(result);
	}

	@Override
	public Wrapper<String> getMainImage(@RequestParam("productId") Long productId) {
		String mainImage = mdcProductService.getMainImage(productId);
		return WrapMapper.ok(mainImage);
	}
}

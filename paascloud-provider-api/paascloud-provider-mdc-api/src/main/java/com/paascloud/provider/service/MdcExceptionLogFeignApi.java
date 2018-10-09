package com.paascloud.provider.service;

import com.paascloud.provider.model.dto.GlobalExceptionLogDto;
import com.paascloud.provider.service.hystrix.MdcExceptionLogFeignHystrix;
import com.passcloud.common.util.wrapper.Wrapper;
import com.passcloud.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The interface Mdc product feign api.
 *
 * @author liyuzhang
 */
@FeignClient(value = "paascloud-provider-mdc", configuration = OAuth2FeignAutoConfiguration.class, fallback = MdcExceptionLogFeignHystrix.class)
public interface MdcExceptionLogFeignApi {

	/**
	 * Update product stock by id int.
	 *
	 * @param exceptionLogDto the exception log dto
	 *
	 * @return the int
	 */
	@PostMapping(value = "/api/exception/saveAndSendExceptionLog")
	Wrapper saveAndSendExceptionLog(@RequestBody GlobalExceptionLogDto exceptionLogDto);
}

package com.paascloud.provider.model.service;


import com.paascloud.provider.model.service.hystrix.UacUserTokenFeignApiHystrix;
import com.passcloud.common.util.wrapper.Wrapper;
import com.passcloud.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The interface Uac user token feign api.
 *
 * @author liyuzhang
 */
@FeignClient(value = "paascloud-provider-uac", configuration = OAuth2FeignAutoConfiguration.class, fallback = UacUserTokenFeignApiHystrix.class)
public interface UacUserTokenFeignApi {

	/**
	 * 超时token更新为离线.
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/uac/token/updateTokenOffLine")
	Wrapper<Integer> updateTokenOffLine();
}

package com.paascloud.provider.service;

import com.paascloud.provider.model.vo.CartVo;
import com.paascloud.provider.service.hystrix.OmcCartQueryFeignHystrix;
import com.passcloud.common.util.wrapper.Wrapper;
import com.passcloud.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Omc cart query feign api.
 *
 * @author liyuzhang
 */
@FeignClient(value = "paascloud-provider-omc", configuration = OAuth2FeignAutoConfiguration.class, fallback = OmcCartQueryFeignHystrix.class)
public interface OmcCartQueryFeignApi {

	/**
	 * Gets cart vo.
	 *
	 * @param userId the user id
	 *
	 * @return the cart vo
	 */
	@PostMapping(value = "/api/cart/getCarVo")
	Wrapper<CartVo> getCartVo(@RequestParam("userId") Long userId);
}

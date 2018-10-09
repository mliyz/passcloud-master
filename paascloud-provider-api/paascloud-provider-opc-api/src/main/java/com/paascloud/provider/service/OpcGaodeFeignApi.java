package com.paascloud.provider.service;

import com.paascloud.provider.model.dto.gaode.GaodeLocation;
import com.paascloud.provider.service.hystrix.OpcGaodeFeignApiHystrix;
import com.passcloud.common.util.wrapper.Wrapper;
import com.passcloud.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Opc gao feign api.
 *
 * @author liyuzhang
 */
@FeignClient(value = "paascloud-provider-opc", configuration = OAuth2FeignAutoConfiguration.class, fallback = OpcGaodeFeignApiHystrix.class)
public interface OpcGaodeFeignApi {

	/**
	 * IP定位.
	 *
	 * @param ipAddr the ip addr
	 *
	 * @return the location by ip addr
	 */
	@PostMapping(value = "/api/auth/getLocationByIpAddr")
	Wrapper<GaodeLocation> getLocationByIpAddr(@RequestParam("ipAddr") String ipAddr);
}

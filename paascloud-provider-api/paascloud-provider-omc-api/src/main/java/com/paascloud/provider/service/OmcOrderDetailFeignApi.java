package com.paascloud.provider.service;

import com.paascloud.provider.service.hystrix.OmcOrderDetailFeignHystrix;
import com.passcloud.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Omc order detail feign api.
 *
 * @author liyuzhang
 */
@FeignClient(value = "paascloud-provider-omc", configuration = OAuth2FeignAutoConfiguration.class, fallback = OmcOrderDetailFeignHystrix.class)
public interface OmcOrderDetailFeignApi {
}

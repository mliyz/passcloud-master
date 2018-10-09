package com.paascloud.provider.service;

import com.paascloud.provider.service.hystrix.MdcProductCategoryFeignHystrix;
import com.passcloud.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Mdc product category feign api.
 *
 * @author liyuzhang
 */
@FeignClient(value = "paascloud-provider-mdc", configuration = OAuth2FeignAutoConfiguration.class, fallback = MdcProductCategoryFeignHystrix.class)
public interface MdcProductCategoryFeignApi {

}

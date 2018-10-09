package com.paascloud.provider.model.service.hystrix;


import com.paascloud.provider.model.service.UacUserTokenFeignApi;
import com.passcloud.common.util.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * The class Uac user token feign api hystrix.
 *
 * @author liyuzhang
 */
@Component
public class UacUserTokenFeignApiHystrix implements UacUserTokenFeignApi {

	@Override
	public Wrapper<Integer> updateTokenOffLine() {
		return null;
	}
}

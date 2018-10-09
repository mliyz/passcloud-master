package com.paascloud.provider.service.hystrix;

import com.paascloud.provider.model.dto.gaode.GaodeLocation;
import com.paascloud.provider.service.OpcGaodeFeignApi;
import com.passcloud.common.util.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * The class Opc oss feign api hystrix.
 *
 * @author liyuzhang
 */
@Component
public class OpcGaodeFeignApiHystrix implements OpcGaodeFeignApi {

	@Override
	public Wrapper<GaodeLocation> getLocationByIpAddr(final String ipAddr) {
		return null;
	}
}

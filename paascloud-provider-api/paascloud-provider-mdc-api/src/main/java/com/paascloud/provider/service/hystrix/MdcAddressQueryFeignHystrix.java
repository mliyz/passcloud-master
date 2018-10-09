package com.paascloud.provider.service.hystrix;

import com.paascloud.provider.model.dto.AddressDTO;
import com.paascloud.provider.service.MdcAddressQueryFeignApi;
import com.passcloud.common.util.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * The class Mdc product query feign hystrix.
 *
 * @author liyuzhang
 */
@Component
public class MdcAddressQueryFeignHystrix implements MdcAddressQueryFeignApi {

	@Override
	public Wrapper<AddressDTO> getById(final Long addressId) {
		return null;
	}
}

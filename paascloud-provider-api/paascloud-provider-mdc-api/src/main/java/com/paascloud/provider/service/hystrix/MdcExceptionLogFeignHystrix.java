package com.paascloud.provider.service.hystrix;

import com.paascloud.provider.model.dto.GlobalExceptionLogDto;
import com.paascloud.provider.service.MdcExceptionLogFeignApi;
import com.passcloud.common.util.wrapper.Wrapper;
import org.springframework.stereotype.Component;


/**
 * The class Mdc exception log feign hystrix.
 *
 * @author liyuzhang
 */
@Component
public class MdcExceptionLogFeignHystrix implements MdcExceptionLogFeignApi {

	@Override
	public Wrapper saveAndSendExceptionLog(final GlobalExceptionLogDto exceptionLogDto) {
		return null;
	}
}

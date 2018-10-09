package com.paascloud.provider.web.rpc;

import com.paascloud.provider.model.service.UacUserTokenFeignApi;
import com.paascloud.provider.service.UacUserTokenService;
import com.passcloud.common.core.support.BaseController;
import com.passcloud.common.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 用户token.
 *
 * @author liyuzhang
 */
@RestController
@Api(value = "API - UacUserTokenFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacUserTokenFeignClient extends BaseController implements UacUserTokenFeignApi {
	@Resource
	private UacUserTokenService uacUserTokenService;

	@Override
	@ApiOperation(httpMethod = "POST", value = "更新token离线状态")
	public Wrapper<Integer> updateTokenOffLine() {
		int result = uacUserTokenService.batchUpdateTokenOffLine();
		return handleResult(result);
	}
}

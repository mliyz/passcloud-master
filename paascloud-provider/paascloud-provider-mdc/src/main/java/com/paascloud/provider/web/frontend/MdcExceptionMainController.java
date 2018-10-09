package com.paascloud.provider.web.frontend;

import com.github.pagehelper.PageInfo;
import com.paascloud.provider.model.dto.MdcExceptionQueryDto;
import com.paascloud.provider.service.MdcExceptionLogService;
import com.passcloud.common.core.support.BaseController;
import com.passcloud.common.util.wrapper.WrapMapper;
import com.passcloud.common.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 异常管理.
 *
 * @author liyuzhang
 */
@RestController
@RequestMapping(value = "/exception", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - MdcExceptionMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdcExceptionMainController extends BaseController {
	@Resource
	private MdcExceptionLogService mdcExceptionLogService;

	/**
	 * 异常日志列表.
	 *
	 * @param mdcExceptionQueryDto the mdc exception query dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/queryListWithPage")
	@ApiOperation(httpMethod = "POST", value = "查询日志列表")
	public Wrapper queryLogListWithPage(@ApiParam(name = "mdcExceptionQueryDto", value = "异常查询条件") @RequestBody MdcExceptionQueryDto mdcExceptionQueryDto) {
		logger.info("查询日志处理列表 mdcExceptionQueryDto={}", mdcExceptionQueryDto);
		PageInfo pageInfo = mdcExceptionLogService.queryExceptionListWithPage(mdcExceptionQueryDto);
		return WrapMapper.ok(pageInfo);
	}
}

package com.paascloud.provider.web.rpc;

import com.paascloud.provider.service.MdcProductCategoryFeignApi;
import com.passcloud.common.core.support.BaseController;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class Mdc product category feign client.
 *
 * @author liyuzhang
 */
@RefreshScope
@RestController
@Api(value = "API - MdcProductCategoryFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdcProductCategoryFeignClient extends BaseController implements MdcProductCategoryFeignApi {
}

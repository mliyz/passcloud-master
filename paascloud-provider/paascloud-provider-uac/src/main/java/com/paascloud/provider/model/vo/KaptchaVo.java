package com.paascloud.provider.model.vo;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * The class Kaptcha vo.
 *
 * @author liyuzhang
 */
@Data
@Api
public class KaptchaVo {
	private String cookieCode;
	private String base64Code;
	private String kaptchaCode;
}

package com.passcloud.security.core.properties;

import lombok.Data;

/**
 * 验证码配置
 *
 * @author liyuzhang
 */
@Data
public class ValidateCodeProperties {

	/**
	 * 图片验证码配置
	 */
	private ImageCodeProperties image = new ImageCodeProperties();
	/**
	 * 短信验证码配置
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();
	/**
	 * 邮箱验证码配置
	 */
	private EmailCodeProperties email = new EmailCodeProperties();

}

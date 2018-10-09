package com.passcloud.security.core.properties;

import lombok.Data;

/**
 * The class O auth 2 properties.
 *
 * 多个接口客户端，是数组，只有一个的话就不用这个了
 *
 * @author liyuzhang
 */
@Data
public class OAuth2Properties {

	/**
	 * 使用jwt时为token签名的秘钥
	 */
	private String jwtSigningKey = "paascloud";
	/**
	 * 客户端配置
	 */
	private OAuth2ClientProperties[] clients = {};

}

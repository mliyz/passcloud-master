package com.passcloud.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.passcloud.security.core.social.SocialProperties;;
/**
 * 微信登录配置项
 *
 * @author liyuzhang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeixinProperties extends SocialProperties {

	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
	 */
	private String providerId = "weixin";
}

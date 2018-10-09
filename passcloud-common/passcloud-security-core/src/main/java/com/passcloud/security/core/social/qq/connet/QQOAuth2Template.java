package com.passcloud.security.core.social.qq.connet;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * The class Qqo auth 2 template.
 *
 * QQOAuth2Template处理qq返回的令牌信息
 *
 * @author liyuzhang
 */
@Slf4j
public class QQOAuth2Template extends OAuth2Template {

	/**
	 * Instantiates a new Qqo auth 2 template.
	 *
	 * @param clientId       the client id
	 * @param clientSecret   the client secret
	 * @param authorizeUrl   the authorize url
	 * @param accessTokenUrl the access token url
	 */
	QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
	}

	/**
	 * Post for access grant access grant.
	 *
	 * @param accessTokenUrl the access token url
	 * @param parameters     the parameters
	 *
	 * @return the access grant
	 */
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

		log.info("【QQOAuth2Template】获取accessToke的响应：responseStr={}", responseStr);

		String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
		//http://wiki.connect.qq.com/使用authorization_code获取access_token
		//access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
		String accessToken = StringUtils.substringAfterLast(items[0], "=");
		Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
		String refreshToken = StringUtils.substringAfterLast(items[2], "=");

		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
	}

	/**
	 * Create rest template rest template.
	 *
	 * 坑，日志debug模式才打印出来 处理qq返回的text/html 类型数据
	 *
	 * @return the rest template
	 */
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}

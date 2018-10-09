package com.passcloud.security.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.Assert;

/**
 * The class O auth 2 feign request interceptor.
 *
 * @author liyuzhang
 */
@Slf4j
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	
	private static final String BEARER_TOKEN_TYPE = "bearer ";

	private final OAuth2RestTemplate oAuth2RestTemplate;
	
	@Autowired
	private OAuth2ClientContext context;

	/**
	 * Instantiates a new O auth 2 feign request interceptor.
	 *
	 * @param oAuth2RestTemplate the o auth 2 rest template
	 */
	OAuth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
		Assert.notNull(oAuth2RestTemplate, "Context can not be null");
		this.oAuth2RestTemplate = oAuth2RestTemplate;
	}

	/**
	 * Apply.
	 *  开放权限,利用url规范来规划客户端的url不通过auth2鉴权,这里唯一的区别是在feign拦截器里处理的逻辑
	 * @param template the template
	 */
	@Override
	public void apply(RequestTemplate template) {
		log.debug("Constructing Header {} for Token {}", HttpHeaders.AUTHORIZATION, BEARER_TOKEN_TYPE);
//		template.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER_TOKEN_TYPE, oAuth2RestTemplate.getAccessToken().toString()));
		if(context.getAccessToken() != null && context.getAccessToken().getValue() != null && OAuth2AccessToken.BEARER_TYPE.equalsIgnoreCase(context.getAccessToken().getTokenType()) ){
			template.header(AUTHORIZATION_HEADER, String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, context.getAccessToken().getValue()));
		}
	}
}

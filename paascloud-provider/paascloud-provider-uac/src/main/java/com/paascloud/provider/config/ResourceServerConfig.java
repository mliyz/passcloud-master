package com.paascloud.provider.config;

import com.paascloud.provider.security.PcSecurityExpressionHandler;
import com.passcloud.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

/**
 * The class Resource server config.
 *
 * @author liyuzhang
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	private PcSecurityExpressionHandler pcSecurityExpressionHandler;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.headers().frameOptions().disable()
				.and()
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and()
				.authorizeRequests().antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
					SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
					SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
					SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL,
					SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*", "/pay/alipayCallback",
					"/druid/**", "/auth/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs").permitAll()
				.anyRequest().authenticated();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.expressionHandler(pcSecurityExpressionHandler);
	}
}

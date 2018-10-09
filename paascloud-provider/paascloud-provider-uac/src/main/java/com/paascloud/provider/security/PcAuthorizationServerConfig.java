package com.paascloud.provider.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * The class Pc authorization server config.
 *
 * @author liyuzhang
 */
@Configuration
@EnableAuthorizationServer
public class PcAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	//token存在redis，默认是在内存
	private TokenStore tokenStore;
	
	private AuthenticationManager authenticationManager;
	
	private UserDetailsService userDetailsService;
	@Resource
	private RestClientDetailsServiceImpl restClientDetailsService;
	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired(required = false)
	private TokenEnhancer jwtTokenEnhancer;

	/**
	 * Configure.
	 *
	 * @param security the security
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()");
		security.allowFormAuthenticationForClients();
	}

	/**
	 * Configure.
	 *
	 * 功能：认证服务器会给哪些第三方应用发令牌
	 *        覆盖了该方法，application.properties里配置的
	 *                 security.oauth2.client.clientId =
	 *                security.oauth2.client.clientSecret =
	 *     就失效了
	 *
	 * @param clients the clients
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(restClientDetailsService);
	}

	/**
	 * Configure.
	 *
	 * @param endpoints the endpoints
	 *
	 *  配置TokenEndpoint 是  /oauth/token处理的入口点
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);

		if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
			TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
			List<TokenEnhancer> enhancers = new ArrayList<>();
			enhancers.add(jwtTokenEnhancer);
			enhancers.add(jwtAccessTokenConverter);
			enhancerChain.setTokenEnhancers(enhancers);
			endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
		}
	}

	/**
	 * 退出时的处理策略配置
	 *
	 * @return logout success handler
	 */
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new PcLogoutSuccessHandler();
	}
}

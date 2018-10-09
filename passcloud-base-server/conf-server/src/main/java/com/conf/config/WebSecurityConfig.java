package com.conf.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *  TODO 版本升级为 Spring Cloud Finchley.SR1  security注册中心没有注册实例
 *  因为 Spring Security 默认开启了所有 CSRF 攻击防御，需要禁用 /eureka 的防御。
 *
 *  @author liyuzhang
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().ignoringAntMatchers("/**").and().authorizeRequests().anyRequest()
				.authenticated().and().httpBasic();
		super.configure(http);
	}
	
}

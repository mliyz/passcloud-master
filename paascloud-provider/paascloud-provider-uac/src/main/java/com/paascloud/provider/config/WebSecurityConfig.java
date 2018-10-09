package com.paascloud.provider.config;

import com.paascloud.provider.security.*;
import com.passcloud.security.core.authentication.FormAuthenticationConfig;
import com.passcloud.security.core.authorize.AuthorizeConfigManager;
import com.passcloud.security.core.authorize.PcAuthorizeConfigProvider;
import com.passcloud.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity // 注解开启Spring Security的功能
//WebSecurityConfigurerAdapter:重写它的方法来设置一些web的安全西街
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true) //开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PcAuthenticationFailureHandler pcAuthenticationFailureHandler;
	@Autowired
	private PcAuthenticationSuccessHandler pcAuthenticationSuccessHandler;
	@Autowired
	private PcLogoutSuccessHandler pcLogoutSuccessHandler;
	
	private AccessDeniedHandler pcAccessDeniedHandler;
	
	private FormAuthenticationConfig formAuthenticationConfig;
	
	private AuthorizeConfigManager authorizeConfigManager;
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		//解决静态资源被拦截的问题
//		web.ignoring().antMatchers("/somewhere/**");
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//允许所有用户访问"/"和"/logout"
		http.authorizeRequests()
				.and()
				// 登录成功页面与登录失败页面
				.formLogin()
				//指定登录页是"/login"
//				.loginPage("/login")
				//登录成功后默认跳转到
//				.defaultSuccessUrl("/welcome")
				.successHandler(pcAuthenticationSuccessHandler)
				.failureHandler(pcAuthenticationFailureHandler)
				.permitAll()
				.and()
				//开启cookie保存用户数据
				.rememberMe()
				//设置cookie有效期
				.tokenValiditySeconds(60 * 60 * 24 * 7)
				//设置cookie的私钥
//				.key("")
				// 权限不足,即403时跳转页面
				.and()
				.exceptionHandling()
				.accessDeniedHandler(pcAccessDeniedHandler)
				.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN))
				.and()
				.logout()
//				.logoutUrl("/logout")
				//退出登录后的默认url是"/login"
//				.logoutSuccessUrl("/login")
				.logoutSuccessHandler(pcLogoutSuccessHandler)
				.permitAll();
		//解决非thymeleaf的form表单提交被拦截问题
		http.csrf().disable().anonymous().disable().cors().and().httpBasic();
		
		//解决中文乱码问题
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(systemUserService()).passwordEncoder(passwordEncoder());
		//也可以将用户名密码写在内存，不推荐
//		auth.inMemoryAuthentication().withUser("admin").password("111111").roles("USER");
	}
	
	/**
	 * 设置用户密码的加密方式为MD5加密
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 *从数据库中读取用户信息
	 */
	@Bean
	public UserDetailsService systemUserService() {
		return new UacUserDetailsServiceImpl();
	}
}

package com.paascloud;

import com.netflix.discovery.converters.Auto;
import com.paascloud.provider.service.impl.PcSmsCodeSender;
import com.passcloud.common.core.interceptor.TokenInterceptor;
import com.passcloud.security.core.properties.SecurityProperties;
import com.passcloud.security.core.validate.code.sms.SmsCodeSender;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * The class Paas cloud uac application.
 *
 * @author liyuzhang
 */
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {
		"com.paascloud.provider",
		"com.passcloud.common.conf.properties",
		"com.passcloud.common.core",
		/*"com.passcloud.common.util",
		"com.passcloud.security.feign",*/
		/*"com.passcloud.security.core"*/})
@Import({TokenInterceptor.class, SecurityProperties.class})
@EnableTransactionManagement
@RefreshScope
public class PaasCloudUacApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaasCloudUacApplication.class, args);
	}

	@Bean
	public SpringLiquibase springLiquibase(DataSource dataSource) {

		SpringLiquibase springLiquibase = new SpringLiquibase();

		springLiquibase.setDataSource(dataSource);
		springLiquibase.setChangeLog("classpath:liquibase/index.xml");

		return springLiquibase;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
		return messageSource;
	}

	@Bean
	public SmsCodeSender smsCodeSender() {
		return new PcSmsCodeSender();
	}
}

package com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

//@Configuration
public class GatewayConfiguration  {
	/*@Bean(name="apiKeyResolver")
	public KeyResolver apiKeyResolver() {
		//根据api接口来限流
		return exchange -> {
			return Mono.just(exchange.getRequest().getPath().value());
		};
	}*/
}

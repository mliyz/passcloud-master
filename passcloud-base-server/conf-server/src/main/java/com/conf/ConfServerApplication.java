package com.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 微服务配置中心
 *
 * @author liyuzhang
 * @Date 2018/08/14
 */
@SpringCloudApplication
@EnableConfigServer
public class ConfServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfServerApplication.class, args);
	}
}

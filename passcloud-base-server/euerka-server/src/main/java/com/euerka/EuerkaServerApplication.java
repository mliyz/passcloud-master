package com.euerka;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringCloudApplication
@EnableEurekaServer
public class EuerkaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EuerkaServerApplication.class, args);
	}
}

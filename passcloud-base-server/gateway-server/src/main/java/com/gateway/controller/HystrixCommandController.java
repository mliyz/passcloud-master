package com.gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HystrixCommandController {
//	@RequestMapping("/hystrixTimeout")
//	public JsonPackage hystrixTimeout() {
//		log.error("i5xforyou-service-gateway触发了断路由");
//		return JsonPackage.getHystrixJsonPackage();
//	}
//
//	@HystrixCommand(commandKey="authHystrixCommand")
//	public JsonPackage authHystrixCommand() {
//		return JsonPackage.getHystrixJsonPackage();
//	}

}

package com.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 集群监控，必须引入acutor组件。
 *
 * TODO 可以依赖turbine-amqp，由rabbitmq作数据中转
 *
 * @author liyuzhang 2018年8月15日 下午4:27:53
 */
@SpringCloudApplication
@EnableTurbine
public class TurbineServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurbineServerApplication.class, args);
	}
}

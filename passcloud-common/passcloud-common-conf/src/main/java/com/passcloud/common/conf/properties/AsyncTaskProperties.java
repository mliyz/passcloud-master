package com.passcloud.common.conf.properties;

import lombok.Data;

/**
 * The class Async task properties.
 *
 * @author liyuzhang
 */
@Data
public class AsyncTaskProperties {

	private int corePoolSize = 50;

	private int maxPoolSize = 100;

	private int queueCapacity = 10000;

	private int keepAliveSeconds = 3000;

	private String threadNamePrefix = "paascloud-task-executor-";
}

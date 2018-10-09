package com.passcloud.common.conf.properties;


import com.passcloud.common.base.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The class Paascloud properties.
 *
 * @author liyuzhang
 */
@Data
@Configuration
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
public class PaascloudProperties {
	private ReliableMessageProperties message = new ReliableMessageProperties();
	private AliyunProperties aliyun = new AliyunProperties();
	private AsyncTaskProperties task = new AsyncTaskProperties();
	private SwaggerProperties swagger = new SwaggerProperties();
	private QiniuProperties qiniu = new QiniuProperties();
	private GaodeProperties gaode = new GaodeProperties();
	private JobProperties job = new JobProperties();
	private ZookeeperProperties zk = new ZookeeperProperties();
}

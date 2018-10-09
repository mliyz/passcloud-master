package com.passcloud.common.conf.properties;

import lombok.Data;

/**
 * The class Aliyun properties.
 *
 * @author liyuzhang
 */
@Data
public class AliyunProperties {
	private AliyunKeyProperties key = new AliyunKeyProperties();
	private RocketMqProperties rocketMq = new RocketMqProperties();
	private AliyunSmsProperties sms = new AliyunSmsProperties();

	@Data
	public class AliyunKeyProperties {
		/**
		 * 秘钥id
		 */
		private String accessKeyId;

		/**
		 * 秘钥
		 */
		private String accessKeySecret;

		public String getAccessKeyId() {
			return accessKeyId;
		}

		public void setAccessKeyId(String accessKeyId) {
			this.accessKeyId = accessKeyId;
		}

		public String getAccessKeySecret() {
			return accessKeySecret;
		}

		public void setAccessKeySecret(String accessKeySecret) {
			this.accessKeySecret = accessKeySecret;
		}
	}

	@Data
	public class RocketMqProperties {
		private String consumerGroup;
		private String producerGroup;
		private String namesrvAddr;
		/**
		 * 生产者是否使用可靠消息, 默认不使用 @MqConsumerStore
		 */
		private boolean reliableMessageProducer;
		/**
		 * 消费者是否使用可靠消息, 默认不使用 @MqProducerStore
		 */
		private boolean reliableMessageConsumer;

		public String getConsumerGroup() {
			return consumerGroup;
		}

		public void setConsumerGroup(String consumerGroup) {
			this.consumerGroup = consumerGroup;
		}

		public String getProducerGroup() {
			return producerGroup;
		}

		public void setProducerGroup(String producerGroup) {
			this.producerGroup = producerGroup;
		}

		public String getNamesrvAddr() {
			return namesrvAddr;
		}

		public void setNamesrvAddr(String namesrvAddr) {
			this.namesrvAddr = namesrvAddr;
		}

		public boolean isReliableMessageProducer() {
			return reliableMessageProducer;
		}

		public void setReliableMessageProducer(boolean reliableMessageProducer) {
			this.reliableMessageProducer = reliableMessageProducer;
		}

		public boolean isReliableMessageConsumer() {
			return reliableMessageConsumer;
		}

		public void setReliableMessageConsumer(boolean reliableMessageConsumer) {
			this.reliableMessageConsumer = reliableMessageConsumer;
		}
	}

	@Data
	public class AliyunSmsProperties {

		/**
		 * 阿里云管理控制台中配置的短信签名（状态必须是验证通过）
		 */
		private String signName;

		/**
		 * 机房信息
		 */
		private String regionId;

		/**
		 * 端点
		 */
		private String endpoint;

		/**
		 * 端点名称
		 */
		private String endpointName;

		private String product;

		private String domain;

		public String getSignName() {
			return signName;
		}

		public void setSignName(String signName) {
			this.signName = signName;
		}

		public String getRegionId() {
			return regionId;
		}

		public void setRegionId(String regionId) {
			this.regionId = regionId;
		}

		public String getEndpoint() {
			return endpoint;
		}

		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}

		public String getEndpointName() {
			return endpointName;
		}

		public void setEndpointName(String endpointName) {
			this.endpointName = endpointName;
		}

		public String getProduct() {
			return product;
		}

		public void setProduct(String product) {
			this.product = product;
		}

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}
	}
}
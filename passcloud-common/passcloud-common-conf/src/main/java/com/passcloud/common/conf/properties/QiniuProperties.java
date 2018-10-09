package com.passcloud.common.conf.properties;

import lombok.Data;

/**
 * The class Qiniu oss properties.
 *
 * @author liyuzhang
 */
@Data
public class QiniuProperties {
	private QiniuKeyProperties key = new QiniuKeyProperties();
	private QiniuOssProperties oss = new QiniuOssProperties();

	@Data
	public class QiniuKeyProperties {
		private String accessKey;
		private String secretKey;
		
		public String getAccessKey() {
			return accessKey;
		}
		
		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}
		
		public String getSecretKey() {
			return secretKey;
		}
		
		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}
	}

	@Data
	public class QiniuOssProperties {
		private String privateHost;
		private String publicHost;
		private Long fileMaxSize;
		
		public String getPrivateHost() {
			return privateHost;
		}
		
		public void setPrivateHost(String privateHost) {
			this.privateHost = privateHost;
		}
		
		public String getPublicHost() {
			return publicHost;
		}
		
		public void setPublicHost(String publicHost) {
			this.publicHost = publicHost;
		}
		
		public Long getFileMaxSize() {
			return fileMaxSize;
		}
		
		public void setFileMaxSize(Long fileMaxSize) {
			this.fileMaxSize = fileMaxSize;
		}
	}
}

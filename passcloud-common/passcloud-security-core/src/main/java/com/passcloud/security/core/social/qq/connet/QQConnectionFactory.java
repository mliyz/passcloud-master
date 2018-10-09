package com.passcloud.security.core.social.qq.connet;

import com.passcloud.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * The class Qq connection factory.
 *
 * QQConnectionFactory连接服务提供商的工厂类
 *
 * @author liyuzhang
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 * Instantiates a new Qq connection factory.
	 *
	 * @param providerId the provider id
	 * @param appId      the app id
	 * @param appSecret  the app secret
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}

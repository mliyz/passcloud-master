package com.passcloud.security.core.social.qq.config;

import com.passcloud.security.core.properties.QQProperties;
import com.passcloud.security.core.properties.SecurityProperties;
import com.passcloud.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.autoconfigure.SocialAutoConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;


/**
 * The class Qq auto config.
 *
 * QQAuthConfig 针对qq返回结果的一些操作
 *
 * @author liyuzhang
 */
@Configuration
@ConditionalOnProperty(prefix = "paascloud.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
	
	
	
	private final SecurityProperties securityProperties;

	@Autowired
	public QQAutoConfig(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	/**
	 * Create connection factory connection factory.
	 *
	 * @return the connection factory
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}
	
}

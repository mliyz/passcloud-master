package com.passcloud.security.core.social.qq.connet;
import com.passcloud.security.core.social.qq.api.QQ;
import com.passcloud.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;


/**
 * The class Qq service provider.
 *
 * QQServiceProvider连接服务提供商
 *
 * @author liyuzhang
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	private String appId;
	/**
	 * 获取code
	 */
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	/**
	 * 获取access_token 也就是令牌
	 */
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

	/**
	 * Instantiates a new Qq service provider.
	 *
	 * @param appId     the app id
	 * @param appSecret the app secret
	 */
	QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}

	/**
	 * Gets api.
	 *
	 * @param accessToken the access token
	 *
	 * @return the api
	 */
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}

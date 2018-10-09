package com.passcloud.security.core.social.qq.connet;

import com.passcloud.security.core.social.qq.api.QQ;
import com.passcloud.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * The class Qq adapter.
 *
 * QQAdapter 适配spring Social默认的返回信息
 *
 * @author liyuzhang
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/**
	 * Test boolean.
	 *
	 * @param api the api
	 *
	 * @return the boolean
	 */
	@Override
	public boolean test(QQ api) {
		return true;
	}

	/**
	 * Sets connection values.
	 *
	 * @param api    the api
	 * @param values the values
	 */
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();

		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureUrlQq1());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());//openId 唯一标识
	}

	/**
	 * Fetch user profile user profile.
	 *
	 * @param api the api
	 *
	 * @return the user profile
	 */
	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	/**
	 * Update status.
	 *
	 * @param api     the api
	 * @param message the message
	 */
	@Override
	public void updateStatus(QQ api, String message) {
		//do noting
	}

}

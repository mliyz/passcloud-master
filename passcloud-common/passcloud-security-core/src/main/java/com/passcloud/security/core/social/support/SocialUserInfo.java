package com.passcloud.security.core.social.support;

import lombok.Data;

/**
 * The class Social user info.
 *
 * @author liyuzhang
 */
@Data
public class SocialUserInfo {

	private String providerId;

	private String providerUserId;

	private String nickname;

	private String headimg;

}

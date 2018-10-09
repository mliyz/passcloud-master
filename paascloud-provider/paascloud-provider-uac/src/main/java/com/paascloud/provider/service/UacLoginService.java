package com.paascloud.provider.service;


import com.paascloud.provider.model.domain.UacUser;
import com.paascloud.provider.model.dto.user.LoginReqDto;
import com.paascloud.provider.model.dto.user.LoginRespDto;

/**
 * The interface Uac login service.
 *
 * @author liyuzhang
 */
public interface UacLoginService {

	/**
	 * Login after login resp dto.
	 *
	 * @param applicationId the application id
	 *
	 * @return the login resp dto
	 */
	LoginRespDto loginAfter(Long applicationId);
	
	/**
	 * login
	 *
	 * @param uacUser
	 */
	void login(LoginReqDto uacUser);
}

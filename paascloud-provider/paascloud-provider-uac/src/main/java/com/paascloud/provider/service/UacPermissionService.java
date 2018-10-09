package com.paascloud.provider.service;


import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Uac permission service.
 *
 * @author liyuzhang
 */
public interface UacPermissionService {

	/**
	 * Has permission boolean.
	 *
	 * @param authentication the authentication
	 * @param request        the request
	 *
	 * @return the boolean
	 */
	boolean hasPermission(Authentication authentication, HttpServletRequest request);
}

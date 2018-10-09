package com.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.passcloud.common.base.enums.ErrorCodeEnum;
import com.passcloud.common.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class Renew filter
 *
 * @author liyuzhang
 */
@Slf4j
@Component
public class RenewFilter extends ZuulFilter {
	
	private JwtTokenStore jwtTokenStore;
	
	private static final int EXPIRES_IN = 60 * 20;
	/**
	 * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
	 * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
	 * We also support a "static" type for static responses see  StaticResponseFilter.
	 * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
	 *
	 * @return A String representing that type
	 */
	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}
	
	/**
	 * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
	 * important for a filter. filterOrders do not need to be sequential.
	 *
	 * @return the int order of a filter
	 */
	@Override
	public int filterOrder() {
		return 10;
	}
	
	/**
	 * a "true" return from this method means that the run() method should be invoked
	 *
	 * @return true if the run() method should be invoked. false will not invoke the run() method
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	/**
	 * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
	 *
	 * @return Some arbitrary artifact may be returned. Current implementation ignores it.
	 * @throws ZuulException if an error occurs during execution.
	 */
	@Override
	public Object run() throws ZuulException {
		log.info("RenewFilter - token续租...");
		RequestContext requestContext = RequestContext.getCurrentContext();
		try {
			doSomething(requestContext);
		} catch (Exception e) {
			log.error("RenewFilter - token续租. [FAIL] EXCEPTION={}", e.getMessage(), e);
			throw new BusinessException(ErrorCodeEnum.UAC10011041);
		}
		return null;
	}
	
	private void doSomething(RequestContext requestContext) {
		HttpServletRequest request = requestContext.getRequest();
		String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), "bearer ");
		if (org.apache.commons.lang.StringUtils.isEmpty(token)) {
			return;
		}
		OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(token);
		int expiresIn = oAuth2AccessToken.getExpiresIn();
		if (expiresIn < EXPIRES_IN){
			HttpServletResponse response = requestContext.getResponse();
			response.addHeader("Renew-Header", "true");
		}
	}
}


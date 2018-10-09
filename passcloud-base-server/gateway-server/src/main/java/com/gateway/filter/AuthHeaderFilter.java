package com.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.passcloud.common.base.enums.ErrorCodeEnum;
import com.passcloud.common.base.exception.BusinessException;
import com.passcloud.common.core.interceptor.CoreHeaderInterceptor;
import com.passcloud.common.core.utils.RequestUtil;
import com.passcloud.common.util.PublicUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * The class Auth header filter
 *
 * @author liyuzhnag
 * @date 2018/08/15
 */
@Slf4j
@Component
public class AuthHeaderFilter extends ZuulFilter{

	private static final String BEARER_TOKEN_TYPE = "bearer ";
	private static final String OPTIONS = "OPTIONS";
	private static final String AUTH_PATH = "/auth";
	private static final String LOGOUT_URI = "/oauth/token";
	private static final String ALIPAY_CALL_URI = "/pay/alipayCallback";

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
		return FilterConstants.PRE_TYPE;
	}

	/**
	 * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
	 * important for a filter. filterOrders do not need to be sequential.
	 *
	 * @return the int order of a filter
	 */
	@Override
	public int filterOrder() {
		return 0;
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
		log.info("AuthHeaderFilter - 开始鉴权... ");
		RequestContext requestContext = RequestContext.getCurrentContext();
		try {
			doSomething(requestContext);
		} catch (Exception e) {
			log.error("AuthHeaderFilter - [FAIL] EXCEPTION={}",e.getMessage(), e);
			throw new BusinessException(ErrorCodeEnum.UAC10011041);
		}
		return null;
	}

	private void doSomething(RequestContext requestContext) throws ZuulException {
		HttpServletRequest request = requestContext.getRequest();
		String requestURI = request.getRequestURI();
		if (OPTIONS.equalsIgnoreCase(request.getMethod()) || !requestURI.contains(AUTH_PATH) || !requestURI.contains(LOGOUT_URI) || !requestURI.contains(ALIPAY_CALL_URI)) {
			return;
		}
		String authHeader = RequestUtil.getAuthHeader(request);
		if (PublicUtil.isEmpty(authHeader)){
			throw new ZuulException("刷新页面重试", 403, "check token fail");
		}
		if(authHeader.startsWith(BEARER_TOKEN_TYPE)){
			requestContext.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);
			log.info("authHeader={} ", authHeader);
			// 传递给后续微服务
			requestContext.addZuulRequestHeader(CoreHeaderInterceptor.HEADER_LABEL, authHeader);
		}
	}
}

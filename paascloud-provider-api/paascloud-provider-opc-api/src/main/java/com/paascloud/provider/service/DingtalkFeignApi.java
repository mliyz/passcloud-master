package com.paascloud.provider.service;


import com.paascloud.provider.model.dto.robot.ChatRobotMsgDto;
import com.paascloud.provider.service.hystrix.DingtalkFeignApiHystrix;
import com.passcloud.common.util.annotation.NoNeedAccessAuthentication;
import com.passcloud.common.util.wrapper.Wrapper;
import com.passcloud.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The interface Dingtalk feign api.
 *
 * @author liyuzhang
 */
@FeignClient(value = "paascloud-provider-opc", configuration = OAuth2FeignAutoConfiguration.class, fallback = DingtalkFeignApiHystrix.class)
public interface DingtalkFeignApi {

	/**
	 * 钉钉机器人推送消息.
	 *
	 * @param uacUserReqDto the uac user req dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/opc/dingtalk/sendChatRobotMsg")
	@NoNeedAccessAuthentication
	Wrapper<Boolean> sendChatRobotMsg(@RequestBody ChatRobotMsgDto uacUserReqDto);
}

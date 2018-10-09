package com.paascloud.provider.service;


import com.github.pagehelper.PageInfo;
import com.paascloud.provider.exceptions.TpcBizException;
import com.paascloud.provider.model.dto.robot.ChatRobotMsgDto;
import com.paascloud.provider.service.DingtalkFeignApi;
import com.paascloud.provider.service.OpcMqMessageFeignApi;
import com.paascloud.provider.service.OpcOssFeignApi;
import com.passcloud.common.base.dto.MessageQueryDto;
import com.passcloud.common.base.dto.MqMessageVo;
import com.passcloud.common.base.enums.ErrorCodeEnum;
import com.passcloud.common.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * The class Opc rpc service.
 *
 * @author liyuzhang
 */
@Slf4j
@Component
public class OpcRpcService {

	@Resource
	private DingtalkFeignApi dingtalkFeignApi;
	@Resource
	private OpcOssFeignApi opcOssFeignApi;
	@Resource
	private OpcMqMessageFeignApi opcMqMessageFeignApi;

	/**
	 * Send chat robot msg boolean.
	 *
	 * @param chatRobotMsgDto the chat robot msg dto
	 *
	 * @return the boolean
	 */
	public boolean sendChatRobotMsg(ChatRobotMsgDto chatRobotMsgDto) {
		Wrapper<Boolean> result = dingtalkFeignApi.sendChatRobotMsg(chatRobotMsgDto);
		return result.getResult();
	}

	/**
	 * Delete expire file.
	 */
	public void deleteExpireFile() {
		opcOssFeignApi.deleteExpireFile();
	}

	public Wrapper<PageInfo<MqMessageVo>> queryMessageListWithPage(final MessageQueryDto messageQueryDto) {
		Wrapper<PageInfo<MqMessageVo>> wrapper = opcMqMessageFeignApi.queryMessageListWithPage(messageQueryDto);
		if (wrapper == null) {
			log.error("查询消息记录 失败 result is null");
			throw new TpcBizException(ErrorCodeEnum.GL99990002);
		}
		return wrapper;
	}
}
package com.paascloud.provider.service;


import com.github.pagehelper.PageInfo;
import com.paascloud.provider.model.domain.UacLog;
import com.paascloud.provider.model.dto.log.UacLogMainDto;
import com.passcloud.common.base.dto.LoginAuthDto;
import com.passcloud.common.core.annotation.OperationLogDto;
import com.passcloud.common.core.support.IService;

import java.util.List;

/**
 * The interface Uac log service.
 *
 * @author liyuzhang
 */
public interface UacLogService extends IService<UacLog> {

	/**
	 * Save log int.
	 *
	 * @param uacLog       the uac log
	 * @param loginAuthDto the login auth dto
	 *
	 * @return the int
	 */
	int saveLog(UacLog uacLog, LoginAuthDto loginAuthDto);

	/**
	 * Query user log list with user id list.
	 *
	 * @param userId the user id
	 *
	 * @return the list
	 */
	List<UacLog> selectUserLogListByUserId(Long userId);

	/**
	 * Save operation log integer.
	 *
	 * @param operationLogDto the operation log dto
	 *
	 * @return the integer
	 */
	Integer saveOperationLog(OperationLogDto operationLogDto);

	/**
	 * Query log list with page wrapper.
	 *
	 * @param uacLogQueryDtoPage the uac log query dto page
	 *
	 * @return the wrapper
	 */
	PageInfo queryLogListWithPage(UacLogMainDto uacLogQueryDtoPage);
}

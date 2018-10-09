package com.paascloud.provider.mapper;

import com.paascloud.provider.model.domain.MdcExceptionLog;
import com.paascloud.provider.model.dto.MdcExceptionQueryDto;
import com.passcloud.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Mdc exception log mapper.
 *
 * @author liyuzhang
 */
@Mapper
@Component
public interface MdcExceptionLogMapper extends MyMapper<MdcExceptionLog> {
	/**
	 * Query exception list with page list.
	 *
	 * @param mdcExceptionQueryDto the mdc exception query dto
	 *
	 * @return the list
	 */
	List<MdcExceptionLog> queryExceptionListWithPage(MdcExceptionQueryDto mdcExceptionQueryDto);
}
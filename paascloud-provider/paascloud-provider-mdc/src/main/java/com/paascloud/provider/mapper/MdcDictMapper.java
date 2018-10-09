package com.paascloud.provider.mapper;

import com.paascloud.provider.model.domain.MdcDict;
import com.paascloud.provider.model.vo.MdcDictVo;
import com.passcloud.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Mdc dict mapper.
 *
 * @author liyuzhang
 */
@Mapper
@Component
public interface MdcDictMapper extends MyMapper<MdcDict> {
	/**
	 * List dict vo list.
	 *
	 * @return the list
	 */
	List<MdcDictVo> listDictVo();
}
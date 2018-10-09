package com.paascloud.provider.mapper;

import com.paascloud.provider.model.domain.UacUserMenu;
import com.passcloud.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The interface Uac user menu mapper.
 *
 * @author liyuzhang
 */
@Mapper
@Component
public interface UacUserMenuMapper extends MyMapper<UacUserMenu> {
}
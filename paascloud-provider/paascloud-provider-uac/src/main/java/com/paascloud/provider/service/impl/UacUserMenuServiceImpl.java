package com.paascloud.provider.service.impl;


import com.paascloud.provider.model.domain.UacUserMenu;
import com.paascloud.provider.service.UacUserMenuService;
import com.passcloud.common.core.support.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The class Uac user menu service.
 *
 * @author liyuzhang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UacUserMenuServiceImpl extends BaseService<UacUserMenu> implements UacUserMenuService {
}

package com.paascloud.provider.service.impl;

import com.google.common.base.Preconditions;
import com.paascloud.provider.model.constant.UacConstant;
import com.paascloud.provider.model.domain.UacUser;
import com.paascloud.provider.model.dto.user.LoginReqDto;
import com.paascloud.provider.model.dto.user.LoginRespDto;
import com.paascloud.provider.model.exceptions.UacBizException;
import com.paascloud.provider.model.vo.MenuVo;
import com.paascloud.provider.security.SecurityUtils;
import com.paascloud.provider.service.UacLoginService;
import com.paascloud.provider.service.UacMenuService;
import com.paascloud.provider.service.UacUserService;
import com.paascloud.provider.utils.Md5Util;
import com.passcloud.common.base.dto.LoginAuthDto;
import com.passcloud.common.base.enums.ErrorCodeEnum;
import com.passcloud.common.util.PublicUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * The class Uac login service.
 *
 * @author liyuzhang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UacLoginServiceImpl implements UacLoginService {

	@Resource
	private UacUserService uacUserService;
	@Resource
	private UacMenuService uacMenuService;

	@Override
	public LoginRespDto loginAfter(Long applicationId) {
		LoginRespDto loginRespDto = new LoginRespDto();
		String loginName = SecurityUtils.getCurrentLoginName();
		if (StringUtils.isEmpty(loginName)) {
			log.error("操作超时, 请重新登录 loginName={}", loginName);
			Preconditions.checkArgument(StringUtils.isNotEmpty(loginName), "操作超时, 请重新登录");
		}

		UacUser uacUser = uacUserService.findByLoginName(loginName);
		if (PublicUtil.isEmpty(uacUser)) {
			log.info("找不到用户信息 loginName={}", loginName);
			throw new UacBizException(ErrorCodeEnum.UAC10011002, loginName);
		}

		LoginAuthDto loginAuthDto = this.getLoginAuthDto(uacUser);
		List<MenuVo> menuVoList = uacMenuService.getMenuVoList(uacUser.getId(), applicationId);
		if (PublicUtil.isNotEmpty(menuVoList) && UacConstant.MENU_ROOT.equals(menuVoList.get(0).getMenuCode())) {
			menuVoList = menuVoList.get(0).getSubMenu();
		}
		loginRespDto.setLoginAuthDto(loginAuthDto);
		loginRespDto.setMenuList(menuVoList);
		return loginRespDto;
	}
	
	/**
	 * login
	 *
	 * @param uacUser
	 */
	@Override
	public void login(LoginReqDto uacUser) {
		String loginName = uacUser.getLoginName();
		if (StringUtils.isEmpty(loginName)) {
			log.error("用户名不能为空, 请重新登录 loginName={}", loginName);
			Preconditions.checkArgument(StringUtils.isNotEmpty(loginName), "用户名不能为空, 请重新登录");
		}
		uacUser.setLoginPwd(Md5Util.encrypt(uacUser.getLoginPwd()));
		String loginPwd = uacUser.getLoginPwd();
		if (StringUtils.isEmpty(loginPwd)) {
			log.error("密码不能为空, 请重新登录 loginName={}", loginPwd);
			Preconditions.checkArgument(StringUtils.isNotEmpty(loginPwd), "密码不能为空, 请重新登录");
		}
		uacUserService.checkUserIsCorrect(uacUser);
	}
	
	private LoginAuthDto getLoginAuthDto(UacUser uacUser) {
		LoginAuthDto loginAuthDto = new LoginAuthDto();
		loginAuthDto.setUserId(uacUser.getId());
		loginAuthDto.setUserName(uacUser.getUserName());
		loginAuthDto.setLoginName(uacUser.getLoginName());
		return loginAuthDto;
	}


}

package com.springboot.tools.handler;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.tools.entity.common.CurrentUser;
import com.springboot.tools.entity.common.ResultGenerator;
import com.springboot.tools.enums.ResultCodeEnum;
import com.springboot.tools.service.impl.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户退出
 * @author swen
 */
@Component
@Slf4j
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {

	private final ObjectMapper mapper = new ObjectMapper();

	@Resource
	private TokenService tokenService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request , HttpServletResponse response , Authentication authentication) throws IOException {
		CurrentUser loginUser = tokenService.getLoginUser(request);
		if (ObjectUtil.isNotEmpty(loginUser)) {
			String userName = loginUser.getUsername();
			log.info(userName + "退出成功");
			// 删除用户缓存记录
			tokenService.delLoginUser(loginUser.getToken());
		}
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(this.mapper.writeValueAsString(ResultGenerator.genResult(ResultCodeEnum.SUCCESS, "退出成功")));
	}

}

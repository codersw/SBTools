package com.springboot.tools.handler;


import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.tools.entity.common.ResultGenerator;
import com.springboot.tools.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 认证失败处理类 返回未授权
 * 用来解决匿名用户访问无权限资源时的异常
 * @author swen
 */
@Component
@Slf4j
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		String msg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
		if(!Objects.equals(request.getMethod(), HttpMethod.OPTIONS.toString())) {
			log.info(msg);
		}
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(this.mapper.writeValueAsString(ResultGenerator.genResult(ResultCodeEnum.UNAUTHORIZED, msg)));
	}
}

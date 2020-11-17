package com.springboot.tools.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.tools.entity.common.ResultGenerator;
import com.springboot.tools.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败
 * @author swen
 */
@Component
@Slf4j
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request , HttpServletResponse response , AuthenticationException exception) throws IOException {
		log.info("登陆失败{}", exception.getMessage());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(this.mapper.writeValueAsString(ResultGenerator.genResult(ResultCodeEnum.UNAUTHORIZED, exception.getMessage())));
	}

}

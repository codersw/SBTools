package com.springboot.tools.service.impl;

import com.springboot.tools.entity.co.LoginCo;
import com.springboot.tools.entity.common.CurrentUser;
import com.springboot.tools.exception.ToolsException;
import com.springboot.tools.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  登陆服务实现类
 * </p>
 *
 * @author swen
 * @since 2020-11-16
 */
@Service
@Transactional
@Slf4j
public class LoginServiceImpl implements ILoginService {

    @Resource
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginCo loginCo) {
        // 用户验证
        Authentication authentication;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginCo.getUsername(), loginCo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登陆失败：{}", e.getMessage());
            if (e instanceof BadCredentialsException) {
                throw new ToolsException("密码不匹配");
            } else {
                throw new ToolsException(e.getMessage());
            }
        }
        CurrentUser loginUser = (CurrentUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}

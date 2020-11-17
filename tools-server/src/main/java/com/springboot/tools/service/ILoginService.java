package com.springboot.tools.service;

import com.springboot.tools.entity.co.LoginCo;

/**
 * <p>
 *  登陆服务类
 * </p>
 *
 * @author swen
 * @since 2020-11-16
 */
public interface ILoginService {

    String login(LoginCo loginCo);
}

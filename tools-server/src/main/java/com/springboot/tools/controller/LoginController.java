package com.springboot.tools.controller;

import com.springboot.tools.entity.co.LoginCo;
import com.springboot.tools.entity.common.CurrentUser;
import com.springboot.tools.entity.common.Result;
import com.springboot.tools.entity.common.ResultGenerator;
import com.springboot.tools.entity.vo.LoginVo;
import com.springboot.tools.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * <p>
 *  登陆接口前端控制器
 * </p>
 *
 * @author swen
 * @since 2020-11-16
 */
@Api(value = "登陆接口", tags = {"登陆接口"})
@Slf4j
@RestController
public class LoginController {

    @Resource
    private ILoginService loginService;

    /**
     * 登录方法
     * @param loginCo 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录方法接口", notes = "登录方法接口")
    public Result<?> login(@RequestBody LoginCo loginCo) {
        return ResultGenerator.genSuccessResult(LoginVo.builder().token(loginService.login(loginCo)).build());
    }

    /**
     * 登陆人信息接口
     * @param currentUser 登陆人信息接口
     * @return Result
     */
    @GetMapping("/current")
    @ApiOperation(value = "登陆人信息接口", notes = "登陆人信息接口")
    public Result<?> current(@ApiIgnore CurrentUser currentUser) {
        return ResultGenerator.genSuccessResult(currentUser);
    }
}

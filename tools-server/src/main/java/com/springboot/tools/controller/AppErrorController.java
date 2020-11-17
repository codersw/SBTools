package com.springboot.tools.controller;



import com.springboot.tools.entity.common.Result;
import com.springboot.tools.entity.common.ResultGenerator;
import com.springboot.tools.enums.ResultCodeEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * 异常改写为restful，默认异常是页面
 * @author swen
 */
@RestController
@ApiIgnore
public class AppErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public Result<?> error(HttpServletResponse response) {
    	int status = response.getStatus();
        switch (status) {
            case 401:
                return ResultGenerator.genResult(ResultCodeEnum.UNAUTHORIZED);
            case 404:
                return ResultGenerator.genResult(ResultCodeEnum.NOT_FOUND);
        }
        return ResultGenerator.genFailResult();
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}

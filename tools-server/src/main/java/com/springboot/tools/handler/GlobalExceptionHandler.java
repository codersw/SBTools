package com.springboot.tools.handler;



import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.springboot.tools.entity.common.Result;
import com.springboot.tools.entity.common.ResultGenerator;
import com.springboot.tools.enums.ResultCodeEnum;
import com.springboot.tools.exception.ToolsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一处理异常
 * @author swen
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static final String DEFAULT_MESSAGE = "系统出错， 请稍后再试";
    private static final String SYSTEM_EXCEPTION = "########## SYSTEM_EXCEPTION ############  %s";
    private static final String APPLICATION_EXCEPTION = "########## APPLICATION_EXCEPTION ############  %s";

    /**
     * ToolsException异常处理
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(value = ToolsException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<?> toolsExceptionHandler(ToolsException e) {
        log.error(String.format(SYSTEM_EXCEPTION, e.toString()), e);
        return ResultGenerator.genResult(ResultCodeEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * Exception异常处理
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<?> defaultErrorHandler(Exception e) {
        log.error(String.format(SYSTEM_EXCEPTION, e.getMessage()), e);
        return ResultGenerator.genResult(ResultCodeEnum.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
    }

    /**
     * RuntimeException异常处理
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<?> runtimeExceptionHandler(RuntimeException e) {
        log.error(String.format(APPLICATION_EXCEPTION, e.toString()), e);
        return ResultGenerator.genResult(ResultCodeEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * MethodArgumentNotValidException异常处理
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> objectErrors = bindingResult.getAllErrors();
        if (CollectionUtils.isNotEmpty(objectErrors)) {
            return ResultGenerator.genFailResult(objectErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(",")));
        } else {
            return ResultGenerator.genFailResult("数据校验失败");
        }
    }

    /**
     * BindException异常处理
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(value = BindException.class)
    public Result<?> BindExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> objectErrors = bindingResult.getAllErrors();
        if (CollectionUtils.isNotEmpty(objectErrors)) {
            return ResultGenerator.genFailResult(objectErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(",")));
        } else {
            return ResultGenerator.genFailResult("数据校验失败");
        }
    }
}

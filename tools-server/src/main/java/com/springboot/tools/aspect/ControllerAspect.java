package com.springboot.tools.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.springboot.tools.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * aop切点记录请求与响应日志
 * @author swen
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    /** 以 controller 包下定义的所有请求为切入点 */
    @Pointcut("execution(public * com.springboot.tools.controller.*.*(..)) || target(com.springboot.tools.handler.GlobalExceptionHandler)))")
    public void webLog() {}

    /**
     * 在切点之前织入
     * @param joinPoint 切点
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", IpUtils.getIpAddr(request));
        try {
            // 打印请求参数
            Object[] paramsArray = joinPoint.getArgs();
            if (paramsArray.length > 0) {
                Object params = paramsArray [0];
                log.info("Request Args   : {}", JSONObject.toJSONString(params, SerializerFeature.IgnoreNonFieldGetter));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("DoBefore Request Args Error", e);
        }

    }

    /**
     * 在切点之后织入
     */
    @After("webLog()")
    public void doAfter() {
        log.info("End RequestId : {}", MDC.get("requestId"));
        // 每个请求之间空一行
        //log.info("");
        //MDC.clear();
    }

    /**
     * 环绕
     * @param proceedingJoinPoint 切点
     * @return Object
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        try {
            // 打印出参
            log.info("Response Args  : {}", JSONObject.toJSONString(result, SerializerFeature.IgnoreNonFieldGetter));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RoAround Response Args Error", e);
        }
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        // 处理完请求，返回内容
        return result;
    }
}

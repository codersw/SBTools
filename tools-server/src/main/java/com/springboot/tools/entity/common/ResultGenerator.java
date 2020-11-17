package com.springboot.tools.entity.common;



import com.springboot.tools.constant.MdcConstant;
import com.springboot.tools.enums.ResultCodeEnum;
import org.slf4j.MDC;

/**
 * 接口返回值生成工具
 * @author swen
 */
public class ResultGenerator {

    /**
     * 成功不返回参数
     * @return Result
     */
    public static Result<?> genSuccessResult() {
        Result<?> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getValue());
        result.setMessage(ResultCodeEnum.SUCCESS.getName());
        result.setRequestId(MDC.get(MdcConstant.REQUEST_ID));
        return result;
    }

    /**
     * 成功返回参数
     * @param data 响应数据
     * @return Result
     */
    public static<T> Result<T> genSuccessResult(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ResultCodeEnum.SUCCESS.getValue());
        result.setMessage(ResultCodeEnum.SUCCESS.getName());
        result.setData(data);
        result.setRequestId(MDC.get(MdcConstant.REQUEST_ID));
        return result;
    }

    /**
     * 失败不返回原因
     * @return Result
     */
    public static Result<?> genFailResult() {
        Result<?> result = new Result<>();
        result.setCode(ResultCodeEnum.FAIL.getValue());
        result.setMessage(ResultCodeEnum.FAIL.getName());
        result.setRequestId(MDC.get(MdcConstant.REQUEST_ID));
        return result;
    }

    /**
     * 失败返回原因
     * @param message 响应消息
     * @return Result
     */
    public static Result<?> genFailResult(String message) {
        Result<?> result = new Result<>();
        result.setCode(ResultCodeEnum.FAIL.getValue());
        result.setMessage(message);
        result.setRequestId(MDC.get(MdcConstant.REQUEST_ID));
        return result;
    }

    /**
     * 自定义创建返回体
     * @param codeEnum 响应码
     * @param message 响应消息
     * @return Result
     */
    public static Result<?> genResult(ResultCodeEnum codeEnum, String message) {
        Result<?> result = new Result<>();
        result.setCode(codeEnum.getValue());
        result.setMessage(message);
        result.setRequestId(MDC.get(MdcConstant.REQUEST_ID));
        return result;
    }

    /**
     * 自定义创建返回体
     * @param codeEnum 响应码
     * @return Result
     */
    public static Result<?> genResult(ResultCodeEnum codeEnum) {
        Result<?> result = new Result<>();
        result.setCode(codeEnum.getValue());
        result.setMessage(codeEnum.getName());
        result.setRequestId(MDC.get(MdcConstant.REQUEST_ID));
        return result;
    }
}

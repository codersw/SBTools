package com.springboot.tools.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 验证码
 * @author swen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="验证码返回", description="验证码返回")
public class CaptchaImageVo {

    private String uuid;

    private String img;
}

package com.springboot.tools.entity.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="登陆信息", description="登陆信息")
public class LoginCo {

    @ApiModelProperty(value = "用户名", example = "用户名")
    private String username;

    @ApiModelProperty(value = "密码", example = "密码")
    private String password;

    @ApiModelProperty(value = "验证码", example = "验证码")
    private String code;

    @ApiModelProperty(value = "唯一标识", example = "唯一标识")
    private String uuid;
}

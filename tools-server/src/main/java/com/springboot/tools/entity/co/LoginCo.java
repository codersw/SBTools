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
}

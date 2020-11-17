package com.springboot.tools.entity.vo;


import com.springboot.tools.entity.pojo.Dept;
import com.springboot.tools.entity.pojo.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息
 * @author swen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVo extends User {

    private Dept dept;
}

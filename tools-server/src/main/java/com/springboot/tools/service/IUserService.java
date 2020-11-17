package com.springboot.tools.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.tools.entity.common.CurrentUser;
import com.springboot.tools.entity.pojo.Role;
import com.springboot.tools.entity.pojo.User;


import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author swen
 * @since 2020-01-20
 */
public interface IUserService extends IService<User> {

    CurrentUser findByUserId(Integer userId);

    User findByUserName(String userName);

    void save(User user, List<Role> roles);
}

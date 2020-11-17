package com.springboot.tools.service.impl;


import com.springboot.tools.entity.pojo.User;
import com.springboot.tools.enums.IsDelEnum;
import com.springboot.tools.enums.UserStatusEnum;
import com.springboot.tools.exception.ToolsException;
import com.springboot.tools.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("securityUserService")
@Slf4j
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if (user == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (UserStatusEnum.BLOCK.getValue().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ToolsException("对不起，您的账号：" + username + " 已被停用");
        } else if (IsDelEnum.TRUE.getValue().equals(user.getIsDel())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new ToolsException("对不起，您的账号：" + username + " 已被删除");
        }
        return userService.findByUserId(user.getUserId());
    }

}

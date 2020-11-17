package com.springboot.tools.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.tools.entity.pojo.UserRole;
import com.springboot.tools.mapper.UserRoleMapper;
import com.springboot.tools.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author swen
 * @since 2020-01-20
 */
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}

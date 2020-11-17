package com.springboot.tools.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.tools.entity.common.CurrentUser;
import com.springboot.tools.entity.pojo.*;
import com.springboot.tools.mapper.DeptMapper;
import com.springboot.tools.mapper.MenuMapper;
import com.springboot.tools.mapper.UserMapper;
import com.springboot.tools.mapper.UserRoleMapper;
import com.springboot.tools.service.IUserService;
import com.springboot.tools.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author swen
 * @since 2020-01-20
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public CurrentUser findByUserId(Integer userId) {
        User user = baseMapper.selectById(userId);
        CurrentUser currentUser = MapperUtils.mapperBean(user, CurrentUser.class);
        String deptName = StrUtil.EMPTY;
        if (ObjectUtil.isNotEmpty(currentUser.getDeptId())) {
            Dept dept = deptMapper.selectById(currentUser.getDeptId());
            if (ObjectUtil.isNotEmpty(dept)) {
                deptName = dept.getDeptName();
            }
        }
        currentUser.setDeptName(deptName);
        List<Menu> menus = menuMapper.getMenus(userId);
        currentUser.setPermissions(menus.stream().map(Menu::getPerms).collect(Collectors.toSet()));
        return currentUser;
    }

    @Override
    public User findByUserName(String userName) {
        return this.baseMapper.findByUserName(userName);
    }

    @Override
    public void save(User user, List<Role> roles) {
        baseMapper.insert(user);
        roles.forEach(role -> this.userRoleMapper.insert(UserRole.builder()
                .roleId(role.getRoleId())
                .userId(user.getUserId())
                .build()));
    }
}

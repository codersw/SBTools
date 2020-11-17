package com.springboot.tools.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.tools.entity.pojo.RoleMenu;
import com.springboot.tools.mapper.RoleMenuMapper;
import com.springboot.tools.service.IRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author swen
 * @since 2020-01-20
 */
@Service
@Transactional
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}

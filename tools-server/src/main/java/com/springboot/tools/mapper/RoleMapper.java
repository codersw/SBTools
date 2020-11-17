package com.springboot.tools.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.tools.entity.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息 Mapper 接口
 * </p>
 *
 * @author swen
 * @since 2020-01-20
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoles(@Param("userId") Integer userId);
}

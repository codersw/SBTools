package com.springboot.tools.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.tools.entity.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author swen
 * @since 2020-01-20
 */
public interface UserMapper extends BaseMapper<User> {

    User findByUserName(@Param("userName") String userName);
}

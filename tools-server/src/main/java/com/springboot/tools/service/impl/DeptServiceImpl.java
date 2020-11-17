package com.springboot.tools.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.tools.entity.pojo.Dept;
import com.springboot.tools.mapper.DeptMapper;
import com.springboot.tools.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  部门服务实现类
 * </p>
 *
 * @author swen
 * @since 2020-01-20
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}

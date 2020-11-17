package com.springboot.tools.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.tools.entity.common.PageRequest;
import com.springboot.tools.entity.common.PageResponse;
import com.springboot.tools.entity.common.Result;
import com.springboot.tools.entity.common.ResultGenerator;
import com.springboot.tools.entity.pojo.Role;
import com.springboot.tools.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 * 角色信息 前端控制器
 * </p>
 *
 * @author swen
 * @since 2020-01-20
 */
@Api(value = "角色信息接口", tags = {"角色信息接口"})
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

     @Resource
     private IRoleService roleService;

     /**
      * 角色信息列表
      * @param role 角色信息
      * @param pageRequest 分页参数
      * @return Result
      */
     @ApiOperation(value = "角色信息列表", notes = "角色信息列表")
     @PreAuthorize("hasAuthority('role:view')")
     @GetMapping
     public Result<?> list(Role role, PageRequest pageRequest) {
     	QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
     	//TODO 设置查询条件

     	Page<Role> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
    	IPage<Role> rolePage = roleService.page(page, queryWrapper);
     	return ResultGenerator.genSuccessResult(PageResponse.<Role>builder().list(rolePage.getRecords()).total(rolePage.getTotal()).build());
     }

     /**
      * 角色信息新增
      * @param role 角色信息
      * @return Result
      */
     @ApiOperation(value = "角色信息新增", notes = "角色信息新增")
     @PreAuthorize("hasAuthority('role:add')")
     @PostMapping
     public Result<?> add(@RequestBody Role role) {
          return ResultGenerator.genSuccessResult(roleService.save(role));
     }

     /**
      * 角色信息删除
      * @param ids 角色信息主键
      * @return Result
      */
     @ApiOperation(value = "角色信息删除", notes = "角色信息删除")
     @PreAuthorize("hasAuthority('role:delete')")
     @DeleteMapping("/{ids}")
     public Result<?> delete(@PathVariable String ids) {
      	return ResultGenerator.genSuccessResult(roleService.removeByIds(Arrays.asList(ids.split(StringPool.COMMA))));
     }

     /**
      * 角色信息修改
      * @param role 角色信息
      * @return Result
      */
     @ApiOperation(value = "角色信息修改", notes = "角色信息修改")
     @PreAuthorize("hasAuthority('role:update')")
     @PutMapping
     public Result<?> update(@RequestBody Role role) {
     	return ResultGenerator.genSuccessResult(roleService.updateById(role));
     }

     /**
      * 角色信息详情
      * @param id 角色信息主键
      * @return Result
      */
     @ApiOperation(value = "角色信息详情", notes = "角色信息详情")
     @PreAuthorize("hasAuthority('role:view')")
     @GetMapping("/{id:\\d+}")
     public Result<?> detail(@PathVariable Integer id) {
     	return ResultGenerator.genSuccessResult(roleService.getById(id));
     }
}

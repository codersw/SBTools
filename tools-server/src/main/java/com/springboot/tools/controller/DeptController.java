package com.springboot.tools.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.springboot.tools.entity.common.PageRequest;
import com.springboot.tools.entity.common.PageResponse;
import com.springboot.tools.entity.common.Result;
import com.springboot.tools.entity.common.ResultGenerator;
import com.springboot.tools.entity.pojo.Dept;
import com.springboot.tools.service.IDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 *  部门信息前端控制器
 * </p>
 *
 * @author swen
 * @since 2020-01-20
 */
@Api(value = "部门信息接口", tags = {"部门信息接口"})
@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController {

     @Resource
     private IDeptService deptService;

     /**
      * 列表
      * @param dept 部门信息
      * @param pageRequest 分页参数
      * @return Result
      */
     @ApiOperation(value = "部门信息列表", notes = "部门信息列表")
     @PreAuthorize("hasAuthority('dept:view')")
     @GetMapping
     public Result<?> list(Dept dept, PageRequest pageRequest) {
     	QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
     	//TODO 设置查询条件

     	Page<Dept> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
     	IPage<Dept> deptPage = deptService.page(page, queryWrapper);
     	return ResultGenerator.genSuccessResult(PageResponse.<Dept>builder().list(deptPage.getRecords()).total(deptPage.getTotal()).build());
     }

     /**
      * 新增
      * @param dept 部门信息
      * @return Result
      */
     @ApiOperation(value = "部门信息新增", notes = "部门信息新增")
     @PreAuthorize("hasAuthority('dept:add')")
     @PostMapping
     public Result<?> add(@RequestBody Dept dept) {
          return ResultGenerator.genSuccessResult(deptService.save(dept));
     }

     /**
      * 删除
      * @param ids 部门信息主键
      * @return Result
      */
     @ApiOperation(value = "部门信息删除", notes = "部门信息删除")
     @PreAuthorize("hasAuthority('dept:delete')")
     @DeleteMapping("/{ids}")
     public Result<?> delete(@PathVariable String ids) {
     	return ResultGenerator.genSuccessResult(deptService.removeByIds(Arrays.asList(ids.split(StringPool.COMMA))));
     }

     /**
      * 修改
      * @param dept 部门信息
      * @return Result
      */
     @ApiOperation(value = "部门信息修改", notes = "部门信息修改")
     @PreAuthorize("hasAuthority('dept:update')")
     @PutMapping
     public Result<?> update(@RequestBody Dept dept) {
     	return ResultGenerator.genSuccessResult(deptService.updateById(dept));
     }

     /**
      * 详情
      * @param id 部门信息主键
      * @return Result
      */
     @ApiOperation(value = "部门信息详情", notes = "部门信息详情")
     @PreAuthorize("hasAuthority('dept:view')")
     @GetMapping("/{id:\\d+}")
     public Result<?> detail(@PathVariable Integer id) {
     	return ResultGenerator.genSuccessResult(deptService.getById(id));
     }
}

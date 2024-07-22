package com.by4cloud.platformx.onlineCourse.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.by4cloud.platformx.common.core.util.R;
import com.by4cloud.platformx.common.log.annotation.SysLog;
import com.by4cloud.platformx.onlineCourse.entity.Demo;
import com.by4cloud.platformx.onlineCourse.service.DemoService;
import org.springframework.security.access.prepost.PreAuthorize;
import com.by4cloud.platformx.common.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * demo
 *
 * @author sxh
 * @date 2024-07-16 16:54:46
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/demo" )
@Tag(description = "demo" , name = "demo管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DemoController {

    private final  DemoService demoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param demo demo
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('onlineCourse_demo_view')" )
    public R getDemoPage(@ParameterObject Page page, @ParameterObject Demo demo) {
        LambdaQueryWrapper<Demo> wrapper = Wrappers.lambdaQuery(demo);
        return R.ok(demoService.page(page, wrapper));
    }


    /**
     * 通过id查询demo
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('onlineCourse_demo_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(demoService.getById(id));
    }

    /**
     * 新增demo
     * @param demo demo
     * @return R
     */
    @Operation(summary = "新增demo" , description = "新增demo" )
    @SysLog("新增demo" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('onlineCourse_demo_add')" )
    public R save(@RequestBody Demo demo) {
		if (ObjectUtil.isNull(demo.getType())) {
			return R.failed("属性:type值为必填");
		}
        return R.ok(demoService.save(demo));
    }

    /**
     * 修改demo
     * @param demo demo
     * @return R
     */
    @Operation(summary = "修改demo" , description = "修改demo" )
    @SysLog("修改demo" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('onlineCourse_demo_edit')" )
    public R updateById(@RequestBody Demo demo) {
        return R.ok(demoService.updateById(demo));
    }

    /**
     * 通过id删除demo
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除demo" , description = "通过id删除demo" )
    @SysLog("通过id删除demo" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('onlineCourse_demo_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(demoService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param demo 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('onlineCourse_demo_export')" )
    public List<Demo> export(Demo demo,Long[] ids) {
        return demoService.list(Wrappers.lambdaQuery(demo).in(ArrayUtil.isNotEmpty(ids), Demo::getId, ids));
    }
}
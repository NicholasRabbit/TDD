package com.by4cloud.platformx.onlineCourse.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.by4cloud.platformx.common.core.util.R;
import com.by4cloud.platformx.common.log.annotation.SysLog;
import com.by4cloud.platformx.onlineCourse.entity.Directory;
import com.by4cloud.platformx.onlineCourse.service.DirectoryService;
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
 * 目录
 *
 * @author pig
 * @date 2024-07-16 17:00:11
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/directory" )
@Tag(description = "directory" , name = "目录管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DirectoryController {

    private final  DirectoryService directoryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param directory 目录
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('onlineCourse_directory_view')" )
    public R getDirectoryPage(@ParameterObject Page page, @ParameterObject Directory directory) {
        LambdaQueryWrapper<Directory> wrapper = Wrappers.lambdaQuery(directory);
        return R.ok(directoryService.page(page, wrapper));
    }


    /**
     * 通过id查询目录
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('onlineCourse_directory_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(directoryService.getById(id));
    }

    /**
     * 新增目录
     * @param directory 目录
     * @return R
     */
    @Operation(summary = "新增目录" , description = "新增目录" )
    @SysLog("新增目录" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('onlineCourse_directory_add')" )
    public R save(@RequestBody Directory directory) {
		if (StringUtils.isEmpty(directory.getDirectoryName())) return R.failed("目录名称不能为空");
		if (ObjectUtil.isNull(directory.getRegulationId())) return R.failed("关联制度id不能为空");
        if (ObjectUtil.isNull(directory.getSuperiorDirectory())) return R.failed("上级目录不能为空");
		return R.ok(directoryService.save(directory));
    }

    /**
     * 修改目录
     * @param directory 目录
     * @return R
     */
    @Operation(summary = "修改目录" , description = "修改目录" )
    @SysLog("修改目录" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('onlineCourse_directory_edit')" )
    public R updateById(@RequestBody Directory directory) {
        return R.ok(directoryService.updateById(directory));
    }

    /**
     * 通过id删除目录
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除目录" , description = "通过id删除目录" )
    @SysLog("通过id删除目录" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('onlineCourse_directory_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(directoryService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param directory 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('onlineCourse_directory_export')" )
    public List<Directory> export(Directory directory,Long[] ids) {
        return directoryService.list(Wrappers.lambdaQuery(directory).in(ArrayUtil.isNotEmpty(ids), Directory::getId, ids));
    }
}
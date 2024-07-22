package com.by4cloud.platformx.onlineCourse.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.by4cloud.platformx.common.core.util.R;
import com.by4cloud.platformx.common.log.annotation.SysLog;
import com.by4cloud.platformx.onlineCourse.entity.RegulationFile;
import com.by4cloud.platformx.onlineCourse.service.RegulationFileService;
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
 * 制度文件表
 *
 * @author lmm
 * @date 2024-07-16 17:12:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/regulationFile" )
@Tag(description = "regulationFile" , name = "制度文件表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class RegulationFileController {

    private final  RegulationFileService regulationFileService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param regulationFile 制度文件表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulationFile_view')" )
    public R getRegulationFilePage(@ParameterObject Page page, @ParameterObject RegulationFile regulationFile) {
        LambdaQueryWrapper<RegulationFile> wrapper = Wrappers.lambdaQuery();
        return R.ok(regulationFileService.page(page, wrapper));
    }


    /**
     * 通过id查询制度文件表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulationFile_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(regulationFileService.getById(id));
    }

    /**
     * 新增制度文件表
     * @param regulationFile 制度文件表
     * @return R
     */
    @Operation(summary = "新增制度文件表" , description = "新增制度文件表" )
    @SysLog("新增制度文件表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulationFile_add')" )
    public R save(@RequestBody RegulationFile regulationFile) {
        return R.ok(regulationFileService.save(regulationFile));
    }

    /**
     * 修改制度文件表
     * @param regulationFile 制度文件表
     * @return R
     */
    @Operation(summary = "修改制度文件表" , description = "修改制度文件表" )
    @SysLog("修改制度文件表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulationFile_edit')" )
    public R updateById(@RequestBody RegulationFile regulationFile) {
        return R.ok(regulationFileService.updateById(regulationFile));
    }

    /**
     * 通过id删除制度文件表
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除制度文件表" , description = "通过id删除制度文件表" )
    @SysLog("通过id删除制度文件表" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulationFile_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(regulationFileService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param regulationFile 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulationFile_export')" )
    public List<RegulationFile> export(RegulationFile regulationFile,Long[] ids) {
        return regulationFileService.list(Wrappers.lambdaQuery(regulationFile).in(ArrayUtil.isNotEmpty(ids), RegulationFile::getId, ids));
    }
}
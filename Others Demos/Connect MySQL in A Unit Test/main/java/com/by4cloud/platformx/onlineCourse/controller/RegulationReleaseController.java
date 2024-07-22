package com.by4cloud.platformx.onlineCourse.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.by4cloud.platformx.common.core.util.R;
import com.by4cloud.platformx.common.log.annotation.SysLog;
import com.by4cloud.platformx.common.security.util.SecurityUtils;
import com.by4cloud.platformx.onlineCourse.constant.StatusConstants;
import com.by4cloud.platformx.onlineCourse.entity.RegulationRelease;
import com.by4cloud.platformx.onlineCourse.service.RegulationReleaseService;
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

/**
 * 制度表
 *
 * @author lmm
 * @date 2024-07-16 17:04:59
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/regulationRelease" )
@Tag(description = "regulationRelease" , name = "制度表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class RegulationReleaseController {

    private final RegulationReleaseService regulationReleaseService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param regulationRelease 制度表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulaitonRelease_view')" )
    public R getRegulationReleasePage(@ParameterObject Page page, @ParameterObject RegulationRelease regulationRelease) {
        LambdaQueryWrapper<RegulationRelease> wrapper = Wrappers.lambdaQuery(regulationRelease);
        return R.ok(regulationReleaseService.page(page, wrapper));
    }


    /**
     * 通过id查询制度表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulaitonRelease_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(regulationReleaseService.getById(id));
    }

    /**
     * 新增制度表
     * @param regulationRelease 制度表
     * @return R
     */
    @Operation(summary = "新增制度表" , description = "新增制度表" )
    @SysLog("新增制度表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('onlineCourse_regulaitonRelease_add')" )
    public R save(@RequestBody RegulationRelease regulationRelease) {
		Long compId = getCompId();
		if (ObjectUtil.isNull(compId))
			return R.failed("无部门信息");
		regulationRelease.setCompId(compId);
		return R.ok(regulationReleaseService.save(regulationRelease));
    }

	protected Long getCompId() {
		return SecurityUtils.getUser().getDeptId();
	}

    /**
     * 修改制度表
     * @param regulationRelease 制度表
     * @return R
     */
    @Operation(summary = "修改制度表" , description = "修改制度表" )
    @SysLog("修改制度表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('onlineCourse_regulaitonRelease_edit')" )
    public R updateById(@RequestBody RegulationRelease regulationRelease) {
		RegulationRelease regulation = regulationReleaseService.getById(regulationRelease.getId());
		if (StatusConstants.YES == regulation.getStatus()) {
			return R.failed("数据已通过审核后不可修改!");
		}
        return R.ok(regulationReleaseService.updateById(regulationRelease));
    }

    /**
     * 通过id删除制度表
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除制度表" , description = "通过id删除制度表" )
    @SysLog("通过id删除制度表" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulaitonRelease_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(regulationReleaseService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param regulationRelease 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('onlineCourse_regulaitonRelease_export')" )
    public List<RegulationRelease> export(RegulationRelease regulationRelease, Long[] ids) {
        return regulationReleaseService.list(Wrappers.lambdaQuery(regulationRelease).in(ArrayUtil.isNotEmpty(ids),
				RegulationRelease::getId, ids));
    }


	@PutMapping("/updateStatus")
	public R updateStatus(@RequestBody RegulationRelease regulationRelease) {
		return R.ok(regulationReleaseService.updateStatus(regulationRelease.getId(), regulationRelease.getStatus()));
	}
}
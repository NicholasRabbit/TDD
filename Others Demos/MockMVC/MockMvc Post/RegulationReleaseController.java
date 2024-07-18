package com.by4cloud.platformx.onlineCourse.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.by4cloud.platformx.common.core.util.R;
import com.by4cloud.platformx.common.log.annotation.SysLog;
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
     * 新增制度表
     * @param regulationRelease 制度表
     * @return R
     */
    @Operation(summary = "新增制度表" , description = "新增制度表" )
    @SysLog("新增制度表" )
    @PostMapping
    public R save(@RequestBody RegulationRelease regulationRelease) {
        return R.ok(regulationReleaseService.save(regulationRelease));
    }

}

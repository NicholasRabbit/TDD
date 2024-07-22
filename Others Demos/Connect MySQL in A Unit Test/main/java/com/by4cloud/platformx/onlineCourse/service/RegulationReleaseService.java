package com.by4cloud.platformx.onlineCourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.by4cloud.platformx.onlineCourse.entity.RegulationRelease;

public interface RegulationReleaseService extends IService<RegulationRelease> {

	boolean updateStatus(Long id, Integer status);

}
package com.by4cloud.platformx.onlineCourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.by4cloud.platformx.onlineCourse.entity.RegulationRelease;
import com.by4cloud.platformx.onlineCourse.mapper.RegulationReleaseMapper;
import com.by4cloud.platformx.onlineCourse.service.RegulationReleaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 制度表
 *
 * @author lmm
 * @date 2024-07-16 17:04:59
 */
@Service
@AllArgsConstructor
public class RegulationReleaseServiceImpl extends ServiceImpl<RegulationReleaseMapper, RegulationRelease> implements RegulationReleaseService {

	private final RegulationReleaseMapper regulationReleaseMapper;

	@Override
	public boolean updateStatus(Long id, Integer status) {
		return regulationReleaseMapper.updateStatus(id, status);
	}


}
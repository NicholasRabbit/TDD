package com.by4cloud.platformx.onlineCourse.mapper;

import com.by4cloud.platformx.common.data.datascope.PlatformxBaseMapper;
import com.by4cloud.platformx.onlineCourse.entity.RegulationRelease;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegulationReleaseMapper extends PlatformxBaseMapper<RegulationRelease> {

	boolean updateStatus(Long id, int status);

}
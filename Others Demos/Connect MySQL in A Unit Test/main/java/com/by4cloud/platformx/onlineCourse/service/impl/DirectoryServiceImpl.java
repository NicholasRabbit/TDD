package com.by4cloud.platformx.onlineCourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.by4cloud.platformx.onlineCourse.entity.Directory;
import com.by4cloud.platformx.onlineCourse.mapper.DirectoryMapper;
import com.by4cloud.platformx.onlineCourse.service.DirectoryService;
import org.springframework.stereotype.Service;
/**
 * 目录
 *
 * @author pig
 * @date 2024-07-16 17:00:11
 */
@Service
public class DirectoryServiceImpl extends ServiceImpl<DirectoryMapper, Directory> implements DirectoryService {
}
package com.by4cloud.platformx.onlineCourse.service;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.by4cloud.platformx.onlineCourse.entity.RegulationFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.picocontainer.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 连接配置文件中指定的数据库。
public class RegulationReleaseServiceTest {

	@Autowired
	private RegulationFileService regulationFileService;

	@BeforeEach
	public void setUp() {

	}

	@Test
	public void testIfServiceAvailable() throws Exception {
		List<RegulationFile> list = regulationFileService.list();
		assertEquals(1, list.size());
	}


}
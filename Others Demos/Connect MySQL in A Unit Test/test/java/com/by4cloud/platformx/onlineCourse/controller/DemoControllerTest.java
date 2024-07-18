package com.by4cloud.platformx.onlineCourse.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.by4cloud.platformx.common.core.util.R;
import com.by4cloud.platformx.onlineCourse.entity.Demo;
import com.by4cloud.platformx.onlineCourse.service.DemoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DemoControllerTest {

	@Mock
	private DemoService demoservice;
	@Mock
	private LambdaQueryWrapper<Demo> wrapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 测试保存Demo实体，其中type类型是必填字段，供后期搜索，实际开发中有可能忘记设置，
	 * 导致后期使用该搜索选项无法找到数据，或者保存垃圾数据。
	 * */
	@Test
	public void shouldSaveDemoWithType() throws Exception {
		Demo demo = mock(Demo.class);
		when(demo.getName()).thenReturn("Jack");
		when(demo.getTest()).thenReturn("test");
		when(demo.getType()).thenReturn(null);
		when(demoservice.save(demo)).thenReturn(true);
		DemoController demoController = new DemoController(demoservice);
		R<Object> expected = R.failed("属性:type值为必填");
		assertEquals(expected.getMsg(), demoController.save(demo).getMsg());
	}


	@AfterEach
	public void tearDown() {

	}
}
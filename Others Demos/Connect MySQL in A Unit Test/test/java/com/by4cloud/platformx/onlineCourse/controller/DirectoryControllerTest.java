package com.by4cloud.platformx.onlineCourse.controller;

import com.by4cloud.platformx.common.core.util.R;
import com.by4cloud.platformx.onlineCourse.entity.Demo;
import com.by4cloud.platformx.onlineCourse.entity.Directory;
import com.by4cloud.platformx.onlineCourse.service.DemoService;
import com.by4cloud.platformx.onlineCourse.service.DirectoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DirectoryControllerTest {
	@Mock
	private DirectoryService directoryService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 测试保存多级目录实体，directoryName属性
	 *
	 * */
	@Test
	public void shouldSaveDirectoryWithDirectoryName() throws Exception {
		Directory directory = mock(Directory.class);
		when(directory.getDirectoryName()).thenReturn(null);
		when(directoryService.save(directory)).thenReturn(true);
		DirectoryController directoryController = new DirectoryController(directoryService);
		R<Object> expected = R.failed("目录名称不能为空");
		assertEquals(expected.getMsg(), directoryController.save(directory).getMsg());
	}

	/**
	 * 测试保存多级目录实体，directoryName属性
	 *
	 * */
	@Test
	public void shouldSaveDirectoryWithRegulationId() throws Exception {
		Directory directory = mock(Directory.class);
		when(directory.getDirectoryName()).thenReturn("根目录");
		when(directory.getRegulationId()).thenReturn(null);
		when(directoryService.save(directory)).thenReturn(true);
		DirectoryController directoryController = new DirectoryController(directoryService);
		R<Object> expected = R.failed("关联制度id不能为空");
		assertEquals(expected.getMsg(), directoryController.save(directory).getMsg());
	}

	/**
	 * 测试保存多级目录实体，superiorDirectory属性
	 *
	 * */
	@Test
	public void shouldSaveDirectoryWithSuperiorDirectory() throws Exception {
		Directory directory = mock(Directory.class);
		when(directory.getDirectoryName()).thenReturn("根目录");
		when(directory.getRegulationId()).thenReturn(1690965893665415170L);
		when(directory.getSuperiorDirectory()).thenReturn(null);
		when(directoryService.save(directory)).thenReturn(true);
		DirectoryController directoryController = new DirectoryController(directoryService);
		R<Object> expected = R.failed("上级目录不能为空");
		assertEquals(expected.getMsg(), directoryController.save(directory).getMsg());
	}


}

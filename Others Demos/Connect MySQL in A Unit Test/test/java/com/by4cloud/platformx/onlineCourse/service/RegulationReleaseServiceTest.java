package com.by4cloud.platformx.onlineCourse.service;

import com.by4cloud.platformx.onlineCourse.mapper.RegulationReleaseMapper;
import com.by4cloud.platformx.onlineCourse.service.impl.RegulationReleaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


public class RegulationReleaseServiceTest {

	@Mock
	private RegulationReleaseMapper mapper;

	private RegulationReleaseService regulationReleaseService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		// 使用真实的service子类对象，但是依赖的Mapper为模拟的
		regulationReleaseService = new RegulationReleaseServiceImpl(mapper);
	}

	@Test
	public void shouldUpdateStatus() throws Exception {

		// 模拟Mapper层的调用行为
		when(mapper.updateStatus(100L, 1)).thenReturn(true);

		boolean actual = regulationReleaseService.updateStatus(100L, 1);
		assertTrue(actual);

		boolean fail = regulationReleaseService.updateStatus(122L, 1);
		assertFalse(fail);

	}

}
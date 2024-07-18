package com.by4cloud.platformx.onlineCourse.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.by4cloud.platformx.common.core.util.R;
import com.by4cloud.platformx.onlineCourse.entity.RegulationRelease;
import com.by4cloud.platformx.onlineCourse.service.RegulationReleaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegulationReleaseControllerTest {

	@Mock
	private RegulationReleaseService regulationReleaseService;
	private MockMvc mvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(new RegulationReleaseController(regulationReleaseService))
				.build();
	}

	@Test
	public void shouldSaveRegulationWithCompId() throws Exception {
		RegulationRelease mock = Mockito.mock(RegulationRelease.class);
		when(mock.getCompId()).thenReturn(null);
		when(mock.getTitle()).thenReturn("煤炭行业安全生培训制度");

		String json = JSONUtil.toJsonStr(mock);
		MockHttpServletRequestBuilder post = post("/regulationRelease")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8");
		MvcResult result= mvc.perform(post)
				.andExpect(status().isOk())
				.andReturn();
		MockHttpServletResponse response = result.getResponse();
		JSONObject jsonObject = new JSONObject(response.getContentAsString());
		R r = jsonObject.toBean(R.class);
		assertEquals("错误提示", r.getMsg());
		assertEquals(1, r.getCode());
	}


	@AfterEach
	public void tearDown() {

	}

}
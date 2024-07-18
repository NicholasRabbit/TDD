package com.by4cloud.platformx.onlineCourse.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.by4cloud.platformx.common.core.util.R;
import com.by4cloud.platformx.common.security.service.PlatformxUser;
import com.by4cloud.platformx.onlineCourse.entity.RegulationRelease;
import com.by4cloud.platformx.onlineCourse.service.RegulationReleaseService;
import org.junit.jupiter.api.*;
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
import static org.mockito.Mockito.mock;
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

	// 测试MockMvc
	@Test
	@Disabled
	public void testMockMvc() throws Exception {
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


	/**
	 * 1, TODO	 无compId
	 * 制度发布时无compId不可进行保存
	 * */
	@Test
	public void shouldFailedIfNoCompIdPresents() throws Exception {
		PlatformxUser user = mock(PlatformxUser.class);
		when(user.getDeptId()).thenReturn(null);
		RegulationRelease mock = Mockito.mock(RegulationRelease.class);
		when(mock.getTitle()).thenReturn("煤炭行业安全生培训制度");

		R expected = R.failed("无部门信息");
		RegulationReleaseController controller = new RegulationReleaseController(regulationReleaseService){
			@Override
			protected Long getCompId() {
				return null;   // 设置前提条件: compId是null，测试是否会返回提示。
			}
		};
		R actual = controller.save(mock);
		assertEquals(expected.getMsg(), actual.getMsg());

	}


	/**
	 * 2， TODO  审核通过后不可修改
	 * */
	@Test
	public void shouldNotBeModifiedIfStatusIsYes() throws Exception{

	}

	@AfterEach
	public void tearDown() {

	}

}
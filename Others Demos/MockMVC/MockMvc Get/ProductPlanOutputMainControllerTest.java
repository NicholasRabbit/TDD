package com.by4cloud.platform.scdd.controller;

import com.by4cloud.platform.scdd.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
public class ProductPlanOutputMainControllerTest {

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	/**
	 * 
	 * */
	@Test
	public void testPlanList() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(
				new ProductPlanOutputMainController())  
				.build();
		mockMvc.perform((MockMvcRequestBuilders
				.get("/productMain/planList")
				.param("planDate", "2023-10-01"))
		);


	}


}

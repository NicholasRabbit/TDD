package com.by4cloud.platformx.onlineCourse.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.by4cloud.platformx.onlineCourse.entity.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 连接配置文件中指定的数据库。
public class DemoMapperTest {

	@Autowired
	private DemoMapper demoMapper;
	private Demo demo;

	@BeforeEach
	public void setUp() throws Exception {
		demo = new Demo();
		demo.setName("Tom");
		demo.setNum(1001);
		demo.setStartTime(new Date());
	}


	@Test
	public void testGetDemoList() throws Exception {
		Wrapper<Demo>  wrapper = Wrappers.lambdaQuery();
		List<Demo> demoList = demoMapper.selectList(wrapper);
		assertEquals(2, demoList.size());
	}

	@Test
	public void testSaveDemo() throws Exception {
		int count = demoMapper.insert(demo);
		assertEquals(1, count);
		List<Demo> demos = demoMapper.selectList(null);
		assertEquals(3, demos.size());
	}


}
package com.by4cloud.platformx.onlineCourse.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.by4cloud.platformx.onlineCourse.entity.RegulationRelease;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 连接配置文件中指定的数据库。
@Sql(scripts = "/regulation_release.sql")
public class RegulationReleaseMapperTest {

	@Autowired
	private RegulationReleaseMapper regulationReleaseMapper;

	@BeforeEach
	public void setUp() {
		//populateTestData();
	}

	private void populateTestData() {
		for (int i = 0; i < 100; i++) {
			RegulationRelease r = new RegulationRelease();
			r.setCompId(100L + i);
			r.setTitle("制度发布测试-" + i);
			r.setContent("内容测试" + (char)i);
			regulationReleaseMapper.insert(r);

		}

	}

	@Test
	@Sql(scripts = "/regulation_release.sql")
	public void testSQLAnnotation() throws Exception {
		List<RegulationRelease> list = regulationReleaseMapper.selectList(null);
		assertEquals(1, list.size());
	}

	// 构建查询条件范例
	@Test
	@Disabled
	public void testSave() throws Exception {
		RegulationRelease entity = new RegulationRelease();
		entity.setCompId(100L);
		regulationReleaseMapper.insert(entity);
		LambdaQueryWrapper<RegulationRelease> wrapper = Wrappers.lambdaQuery(entity);
		RegulationRelease actual = regulationReleaseMapper.selectOne(wrapper);
		assertEquals(100L, actual.getCompId());
	}

	@Test
	@Disabled
	public void shouldSave100Rows() throws Exception{
		List<RegulationRelease> beforeAddList = regulationReleaseMapper.selectList(null);
		int expected = 100;
		populateTestData();  // add 100 rows
		int actual = regulationReleaseMapper.selectList(null).size() - beforeAddList.size();
		assertEquals(expected, actual);
	}

}
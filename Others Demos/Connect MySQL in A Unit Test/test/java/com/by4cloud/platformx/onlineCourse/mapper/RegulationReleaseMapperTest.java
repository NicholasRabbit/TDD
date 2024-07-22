package com.by4cloud.platformx.onlineCourse.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.by4cloud.platformx.onlineCourse.entity.RegulationRelease;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 连接配置文件中指定的数据库。
//@Sql(scripts = "/regulation_release.sql")   // @Sql： 此注解表示默认在测试之前执行指定的sql脚本。
public class RegulationReleaseMapperTest {

	@Autowired
	private RegulationReleaseMapper regulationReleaseMapper;

	@BeforeEach
	public void setUp() {
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
//	@Sql(scripts = "/regulation_release.sql")   // @Sql注解可以用于类和方法上
	public void testSQLAnnotation() throws Exception {
		RegulationRelease queryObj = new RegulationRelease();
		queryObj.setId(1001L);
		LambdaQueryWrapper<RegulationRelease> wrapper = Wrappers.lambdaQuery(queryObj);
		RegulationRelease actual = regulationReleaseMapper.selectOne(wrapper);
		assertEquals(1001L, actual.getId());

	}

	// 构建查询条件范例
	@Test
//	@Commit   // commit transaction after the test
	public void testSave() throws Exception {
		RegulationRelease entity = new RegulationRelease();
		entity.setCompId(100L);
		regulationReleaseMapper.insert(entity);
		LambdaQueryWrapper<RegulationRelease> wrapper = Wrappers.lambdaQuery(entity);
		RegulationRelease actual = regulationReleaseMapper.selectOne(wrapper);
		assertEquals(100L, actual.getCompId());
	}

	@Test
	//@Commit   // commit transaction after the test
	public void shouldSave100Rows() throws Exception {
		List<RegulationRelease> beforeAddList = regulationReleaseMapper.selectList(null);
		int expected = 100;
		populateTestData();  // add 100 rows
		int actual = regulationReleaseMapper.selectList(null).size() - beforeAddList.size();
		assertEquals(expected, actual);
	}

	/**
	 * 测试修改审核状态
	 * */
	@Test
	public void testUpdateStatusById() throws Exception {
		Long id = 1813769868920733697L;   // 注意需提前在@Sql脚本中准备数据，后期随着项目进行数据库中旧数据会删除，导致测试失败
		int status = 1;
		boolean isUpdated = regulationReleaseMapper.updateStatus(id, status);
		assertTrue(isUpdated);
		RegulationRelease actual = regulationReleaseMapper.selectById(id);
		assertEquals(1, actual.getStatus());
	}


	@Test
	public void shouldFindListByCompId() throws Exception {
		LambdaQueryWrapper<RegulationRelease> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(RegulationRelease::getCompId, 4L);
		List<RegulationRelease> listByCompId = regulationReleaseMapper.selectList(wrapper);

		assertTrue(listByCompId.size() > 0);

	}






}
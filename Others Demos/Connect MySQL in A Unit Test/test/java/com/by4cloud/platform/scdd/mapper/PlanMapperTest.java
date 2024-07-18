package com.by4cloud.platform.scdd.mapper;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.by4cloud.platform.scdd.entity.AnnualMonthDailyPlanV;
import com.by4cloud.platform.scdd.entity.ProductPlanOutput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 连接配置文件中指定的数据库。
//@ActiveProfiles("test")
public class PlanMapperTest {

	@Autowired
	private ProductPlanOutputMapper  planOutputMapper;

	@Test
	public void testFindById() throws Exception {
		ProductPlanOutput plan = planOutputMapper.selectById(1697182294512558082L);
		assertNotNull(plan);
	}

	@Test
	public void testSumGroupDailyPlan() throws Exception {
		DateTime date = DateUtil.parse("2023-09-01", "yyyy-MM-dd");
		AnnualMonthDailyPlanV sumPlan = planOutputMapper.sumGroupDailyPlan(date, "3", "0203");
		assertNotNull(sumPlan);
	}



}

package com.by4cloud.platform.scdd.controller;

 * 计划产量主表
 *
 * @author sxh
 * @date 2023-05-26 14:56:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/productMain" )
@Tag(description = "productMain" , name = "计划产量主表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ProductPlanOutputMainController {

	//...
	/**
	 * 运销查询年月日计划接口
	 * */
	@GetMapping("/planList")
	public R planList(@DateTimeFormat(pattern = "yyyy-MM-dd") Date planDate, String type, String compArea){
		if(ObjectUtil.isEmpty(planDate) || StrUtil.isBlank(type) || ObjectUtil.isEmpty(compArea))
			return R.failed("参数不全！");
		return R.ok(productPlanOutputMainService.planList(planDate, type, compArea));
	}

}

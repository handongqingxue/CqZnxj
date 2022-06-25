package com.cqZnxj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private DeptService deptService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private TrackingService trackingService;
	
	/**
	 * Ìø×ªµ½µÇÂ¼Ò³
	 * @return
	 */
	@RequestMapping(value="/goLogin")
	public String goLogin() {
		
		return "login";
	}
	
	//https://blog.csdn.net/qq_19642249/article/details/53845315?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-53845315-blog-114814468.pc_relevant_multi_platform_whitelistv1&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-53845315-blog-114814468.pc_relevant_multi_platform_whitelistv1&utm_relevant_index=1
	//https://blog.csdn.net/weixin_35728532/article/details/116159700
	@RequestMapping(value="/goBaiDuMapTest")
	public String goBaiDuMapTest() {
		
		float areaLongitudeStart=(float)112.380482;
		float areaLongitudeEnd=(float)120.380482;
		float areaLatitudeStart=(float)36.87649;
		float areaLatitudeEnd=(float)41.87649;
		float mapWidth=500;
		float mapHeight=300;
		List<Tracking> trackingList = trackingService.selectCanvasData(1, 2, null, null);
		for (int i = 0; i < trackingList.size(); i++) {
			Tracking tracking = trackingList.get(i);
			float x = Float.valueOf(tracking.getX());
			float y = Float.valueOf(tracking.getY());
			System.out.println("x==="+x);
			System.out.println("y==="+y);
		}
		
		return "baiDuMapTest";
	}

	@RequestMapping(value="/queryDeptTreeList")
	@ResponseBody
	public List<TreeNode> queryDeptTreeList() {

		List<TreeNode> deptList=deptService.queryTreeList(0);
		
		return deptList;
	}
	
	@RequestMapping(value="/queryDeptCBBList")
	@ResponseBody
	public Map<String, Object> queryDeptCBBList(Integer parentId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Dept> deptList=deptService.queryCBBList(parentId);
		
		jsonMap.put("rows", deptList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryStaffCBBList")
	@ResponseBody
	public Map<String, Object> queryStaffCBBList(String deptId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Staff> staffList=staffService.queryCBBList(deptId);
		
		jsonMap.put("rows", staffList);
		
		return jsonMap;
	}
}

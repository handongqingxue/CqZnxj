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
	
	/**
	 * Ìø×ªµ½µÇÂ¼Ò³
	 * @return
	 */
	@RequestMapping(value="/goLogin")
	public String goLogin() {
		
		return "login";
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

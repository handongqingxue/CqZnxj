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
	public List<Dept> queryDeptTreeList() {

		List<Dept> deptList=deptService.queryTreeList();
		
		return deptList;
	}
}

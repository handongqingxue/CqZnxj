package com.cqZnxj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

/**
 * 巡检管理类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/patrolMgmt")
public class PatrolMgmtController {
	@Autowired
	private PatrolAreaService patrolAreaService;
	public static final String MODULE_NAME="patrolMgmt";
	
	@RequestMapping(value="/area/new")
	public String goAreaNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/area/new";
	}

	@RequestMapping(value="/area/list")
	public String goAreaList(HttpServletRequest request) {
		
		return MODULE_NAME+"/area/list";
	}
	
	@RequestMapping(value="/newArea")
	@ResponseBody
	public Map<String, Object> newArea(PatrolArea pa) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolAreaService.add(pa);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建巡检区域成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建巡检区域失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryAreaList")
	@ResponseBody
	public Map<String, Object> queryAreaList(String name,Integer deptId, String deptName,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolAreaService.queryForInt(name,deptId, deptName);
			List<PatrolArea> paList=patrolAreaService.queryList(name, deptId, deptName, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", paList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
}

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
import com.cqZnxj.util.JsonUtil;
import com.cqZnxj.util.PlanResult;

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
	@Autowired
	private PatrolLineService patrolLineService;
	public static final String MODULE_NAME="patrolMgmt";
	
	@RequestMapping(value="/area/new")
	public String goAreaNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/area/new";
	}
	
	@RequestMapping(value="/area/edit")
	public String goAreaEdit(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolArea pa=patrolAreaService.selectById(id);
		request.setAttribute("pa", pa);
		
		return MODULE_NAME+"/area/edit";
	}

	@RequestMapping(value="/area/list")
	public String goAreaList(HttpServletRequest request) {
		
		return MODULE_NAME+"/area/list";
	}
	
	@RequestMapping(value="/area/detail")
	public String goAreaDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolArea pa=patrolAreaService.selectById(id);
		request.setAttribute("pa", pa);
		
		return MODULE_NAME+"/area/detail";
	}

	@RequestMapping(value="/line/list")
	public String goLineList(HttpServletRequest request) {
		
		return MODULE_NAME+"/line/list";
	}
	
	@RequestMapping(value="/newLine")
	@ResponseBody
	public Map<String, Object> newLine(PatrolLine pl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolLineService.add(pl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建巡检路线成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建巡检路线失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryLineList")
	@ResponseBody
	public Map<String, Object> queryLineList(String name,String createTimeStart,String createTimeEnd,
			int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolLineService.queryForInt(name,createTimeStart, createTimeEnd);
			List<PatrolLine> plList=patrolLineService.queryList(name, createTimeStart, createTimeEnd, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", plList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
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

	@RequestMapping(value="/deleteArea",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteParam(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=patrolAreaService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除巡检区域失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除巡检区域成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editArea")
	@ResponseBody
	public Map<String, Object> editArea(PatrolArea pa) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolAreaService.edit(pa);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑巡检区域成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑巡检区域失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryAreaList")
	@ResponseBody
	public Map<String, Object> queryAreaList(String name,Integer deptId, String deptName,String createTimeStart,String createTimeEnd,
			int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolAreaService.queryForInt(name,deptId, deptName,createTimeStart, createTimeEnd);
			List<PatrolArea> paList=patrolAreaService.queryList(name, deptId, deptName,createTimeStart, createTimeEnd, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", paList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
}

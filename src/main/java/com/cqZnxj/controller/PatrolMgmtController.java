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
	@Autowired
	private PatLineAreaAccSetService patLineAreaAccSetService;
	@Autowired
	private PatrolTeamService patrolTeamService;
	@Autowired
	private PatrolPlanService patrolPlanService;
	@Autowired
	private DevParPatRecService devParPatRecService;
	@Autowired
	private LinePatRecService linePatRecService;
	@Autowired
	private AreaPatRecService areaPatRecService;
	@Autowired
	private StaffService staffService;
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

	@RequestMapping(value="/rec/list")
	public String goRecList(HttpServletRequest request) {
		
		return MODULE_NAME+"/rec/list";
	}
	
	@RequestMapping(value="/rec/detail")
	public String goRecDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		DevParPatRec dppr=devParPatRecService.selectById(id);
		request.setAttribute("dppr", dppr);
		
		return MODULE_NAME+"/rec/detail";
	}

	@RequestMapping(value="/cen/info")
	public String goCenInfo(HttpServletRequest request) {
		
		//https://blog.csdn.net/weixin_42418754/article/details/113274973
		return MODULE_NAME+"/cen/info";
	}

	@RequestMapping(value="/line/list")
	public String goLineList(HttpServletRequest request) {
		
		return MODULE_NAME+"/line/list";
	}
	
	@RequestMapping(value="/plan/new")
	public String goPlanNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/plan/new";
	}
	
	@RequestMapping(value="/plan/edit")
	public String goPlanEdit(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolPlan pp=patrolPlanService.selectById(id);
		request.setAttribute("pp", pp);
		
		return MODULE_NAME+"/plan/edit";
	}

	@RequestMapping(value="/plan/list")
	public String goPlanList(HttpServletRequest request) {
		
		return MODULE_NAME+"/plan/list";
	}
	
	@RequestMapping(value="/plan/detail")
	public String goPlanDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolPlan pp=patrolPlanService.selectById(id);
		request.setAttribute("pp", pp);
		
		return MODULE_NAME+"/plan/detail";
	}
	
	@RequestMapping(value="/team/new")
	public String goTeamNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/team/new";
	}
	
	@RequestMapping(value="/team/edit")
	public String goTeamEdit(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolTeam pt=patrolTeamService.selectById(id);
		request.setAttribute("pt", pt);
		
		return MODULE_NAME+"/team/edit";
	}

	@RequestMapping(value="/team/list")
	public String goTeamList(HttpServletRequest request) {
		
		return MODULE_NAME+"/team/list";
	}
	
	@RequestMapping(value="/team/detail")
	public String goTeamDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolTeam pt=patrolTeamService.selectById(id);
		request.setAttribute("pt", pt);
		
		return MODULE_NAME+"/team/detail";
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

	@RequestMapping(value="/deleteLine",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteLine(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=patrolLineService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除巡检路线失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除巡检路线成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editLine")
	@ResponseBody
	public Map<String, Object> editLine(PatrolLine pl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolLineService.edit(pl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑巡检路线成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑巡检路线失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/newPatLineAreaAccSet")
	@ResponseBody
	public Map<String, Object> newPatLineAreaAccSet(PatLineAreaAccSet plaas) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patLineAreaAccSetService.add(plaas);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建巡检路线、区域、设备台账关系成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建巡检路线、区域、设备台账关系失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deletePatLineAreaAccSet",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deletePatLineAreaAccSet(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=patLineAreaAccSetService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除巡检路线、区域、设备台账关系失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除巡检路线、区域、设备台账关系成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editPatLineAreaAccSet")
	@ResponseBody
	public Map<String, Object> editPatLineAreaAccSet(PatLineAreaAccSet plaas) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patLineAreaAccSetService.edit(plaas);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑巡检路线、区域、设备台账关系成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑巡检路线、区域、设备台账关系失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryPatLineAreaAccSetList")
	@ResponseBody
	public Map<String, Object> queryPatLineAreaAccSetList(String plName,String paName,
			int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patLineAreaAccSetService.queryForInt(plName,paName);
			List<PatLineAreaAccSet> plaasList=patLineAreaAccSetService.queryList(plName,paName, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", plaasList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public String deleteArea(String ids) {
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
	public Map<String, Object> queryAreaList(String name,Integer deptId, String secondDeptName,String createTimeStart,String createTimeEnd,
			int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolAreaService.queryForInt(name,deptId, secondDeptName,createTimeStart, createTimeEnd);
			List<PatrolArea> paList=patrolAreaService.queryList(name, deptId, secondDeptName,createTimeStart, createTimeEnd, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", paList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryRecList")
	@ResponseBody
	public Map<String, Object> queryRecList(String plName,String paName,String pdName,String pdaNo,String pdpName,String pdpUnit,Integer pdLevel,
			String startTime,String endTime,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = devParPatRecService.queryForInt(plName, paName, pdName, pdaNo, pdpName, pdpUnit, pdLevel, startTime, endTime);
			List<DevParPatRec> dpprList=devParPatRecService.queryList(plName, paName, pdName, pdaNo, pdpName, pdpUnit, pdLevel,
					 startTime, endTime, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", dpprList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/newPlan")
	@ResponseBody
	public Map<String, Object> newPlan(PatrolPlan pp) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count=patrolPlanService.add(pp);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "创建巡检计划成功！");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "创建巡检计划失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建巡检计划失败！");
			e.printStackTrace();
		}
		return jsonMap;
	}

	@RequestMapping(value="/deletePlan",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deletePlan(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=patrolPlanService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除巡检计划失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除巡检计划成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editPlan")
	@ResponseBody
	public Map<String, Object> editPlan(PatrolPlan pp) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolPlanService.edit(pp);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑巡检计划成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑巡检计划失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryPlanList")
	@ResponseBody
	public Map<String, Object> queryPlanList(String name,Integer state,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolPlanService.queryForInt(name,state);
			List<PatrolPlan> ppList=patrolPlanService.queryList(name, state, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", ppList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/newTeam")
	@ResponseBody
	public Map<String, Object> newTeam(PatrolTeam pt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolTeamService.add(pt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建巡检班组成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建巡检班组失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deleteTeam",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteTeam(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=patrolTeamService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除巡检班组失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除巡检班组成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editTeam")
	@ResponseBody
	public Map<String, Object> editTeam(PatrolTeam pt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolTeamService.edit(pt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑巡检班组成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑巡检班组失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryTeamList")
	@ResponseBody
	public Map<String, Object> queryTeamList(String name,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolTeamService.queryForInt(name);
			List<PatrolTeam> ptList=patrolTeamService.queryList(name, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", ptList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}

	@RequestMapping(value="/getCenAnaData")
	@ResponseBody
	public Map<String, Object> getCenAnaData(Integer ptId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		int reachDayCount=patrolPlanService.getReachDayCount(ptId);
		int sumDayCount=patrolPlanService.getSumDayCount(ptId);
		float lineReachPercent=linePatRecService.getReachPercent(ptId);
		float areaReachPercent=areaPatRecService.getReachPercent(ptId);
		
		jsonMap.put("reachDayCount", reachDayCount);
		jsonMap.put("sumDayCount", sumDayCount);
		jsonMap.put("lineReachPercent", lineReachPercent);
		jsonMap.put("areaReachPercent", areaReachPercent);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryLineCBBList")
	@ResponseBody
	public Map<String, Object> queryLineCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolLine> plList=patrolLineService.queryCBBList();
		
		jsonMap.put("rows", plList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryAreaCBBList")
	@ResponseBody
	public Map<String, Object> queryAreaCBBList(int deptId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolArea> paList=patrolAreaService.queryCBBList(deptId);
		
		jsonMap.put("rows", paList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryTeamCBBList")
	@ResponseBody
	public Map<String, Object> queryTeamCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolTeam> ptList=patrolTeamService.queryCBBList();
		
		jsonMap.put("rows", ptList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryTeamCBBListByDeptId")
	@ResponseBody
	public Map<String, Object> queryTeamCBBListByDeptId(Integer deptId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolTeam> ptList=patrolTeamService.queryCBBListByDeptId(deptId);
		
		jsonMap.put("rows", ptList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryTeamStaffCBBList")
	@ResponseBody
	public Map<String, Object> queryTeamStaffCBBList(Integer ptId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Staff> ptsList=staffService.queryCBBListByPtId(ptId);
		
		jsonMap.put("rows", ptsList);
		
		return jsonMap;
	}
}

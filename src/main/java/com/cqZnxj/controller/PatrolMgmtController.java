package com.cqZnxj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;
import com.cqZnxj.util.*;

import net.sf.json.JSONObject;

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
	private TrackingService trackingService;
	@Autowired
	private StaffService staffService;
	public static final String MODULE_NAME="patrolMgmt";
	
	/**
	 * 跳转到巡检区域添加页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/area/new")
	public String goAreaNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/area/new";
	}
	
	/**
	 * 跳转到巡检区域编辑页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/area/edit")
	public String goAreaEdit(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolArea pa=patrolAreaService.selectById(id);
		request.setAttribute("pa", pa);
		
		return MODULE_NAME+"/area/edit";
	}

	/**
	 * 跳转到巡检区域列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/area/list")
	public String goAreaList(HttpServletRequest request) {
		
		return MODULE_NAME+"/area/list";
	}
	
	/**
	 * 跳转到巡检区域详情页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/area/detail")
	public String goAreaDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolArea pa=patrolAreaService.selectById(id);
		request.setAttribute("pa", pa);
		
		return MODULE_NAME+"/area/detail";
	}
	
	/**
	 * 跳转到巡检人员添加页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/staff/new")
	public String goStaffNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/staff/new";
	}
	
	/**
	 * 跳转到巡检人员列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/staff/list")
	public String goStaffList(HttpServletRequest request) {
		
		return MODULE_NAME+"/staff/list";
	}

	/**
	 * 跳转到巡检记录列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/rec/list")
	public String goRecList(HttpServletRequest request) {
		
		return MODULE_NAME+"/rec/list";
	}

	/**
	 * 跳转到巡检记录详情页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/rec/detail")
	public String goRecDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		DevParPatRec dppr=devParPatRecService.selectById(id);
		request.setAttribute("dppr", dppr);
		
		return MODULE_NAME+"/rec/detail";
	}

	/**
	 * 跳转到巡更统计分析页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cen/info")
	public String goCenInfo(HttpServletRequest request) {
		
		//https://blog.csdn.net/weixin_42418754/article/details/113274973
		return MODULE_NAME+"/cen/info";
	}

	/**
	 * 跳转到巡检路线列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/line/list")
	public String goLineList(HttpServletRequest request) {
		
		return MODULE_NAME+"/line/list";
	}

	/**
	 * 跳转到巡检计划添加页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/plan/new")
	public String goPlanNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/plan/new";
	}

	/**
	 * 跳转到巡检计划编辑页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/plan/edit")
	public String goPlanEdit(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolPlan pp=patrolPlanService.selectById(id);
		request.setAttribute("pp", pp);
		
		return MODULE_NAME+"/plan/edit";
	}

	/**
	 * 跳转到巡检计划列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/plan/list")
	public String goPlanList(HttpServletRequest request) {
		
		return MODULE_NAME+"/plan/list";
	}

	/**
	 * 跳转到巡检计划详情页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/plan/detail")
	public String goPlanDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolPlan pp=patrolPlanService.selectById(id);
		request.setAttribute("pp", pp);
		
		return MODULE_NAME+"/plan/detail";
	}

	/**
	 * 跳转到巡检班组添加页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/team/new")
	public String goTeamNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/team/new";
	}

	/**
	 * 跳转到巡检班组编辑页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/team/edit")
	public String goTeamEdit(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolTeam pt=patrolTeamService.selectById(id);
		request.setAttribute("pt", pt);
		
		return MODULE_NAME+"/team/edit";
	}

	/**
	 * 跳转到巡检班组列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/team/list")
	public String goTeamList(HttpServletRequest request) {
		
		return MODULE_NAME+"/team/list";
	}

	/**
	 * 跳转到巡检班组详情页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/team/detail")
	public String goTeamDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolTeam pt=patrolTeamService.selectById(id);
		request.setAttribute("pt", pt);
		
		return MODULE_NAME+"/team/detail";
	}
	
	/**
	 * 添加巡检路线
	 * @param pl
	 * @return
	 */
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

	/**
	 * 删除巡检路线
	 * @param ids
	 * @return
	 */
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
	
	/**
	 * 编辑巡检路线
	 * @param pl
	 * @return
	 */
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
	
	/**
	 * 添加巡检路线、区域、设备台账关系
	 * @param plaas
	 * @return
	 */
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

	/**
	 * 删除巡检路线、区域、设备台账关系
	 * @param ids
	 * @return
	 */
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
	
	/**
	 * 编辑巡检路线、区域、设备台账关系
	 * @param plaas
	 * @return
	 */
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
	
	/**
	 * 查询巡检路线、区域、设备台账关系列表
	 * @param plName
	 * @param paName
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
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
	
	@RequestMapping(value="/newStaff")
	@ResponseBody
	public Map<String, Object> newStaff(Staff staff,
			@RequestParam(value="photo_file",required=false) MultipartFile photo_file) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			MultipartFile[] fileArr=new MultipartFile[1];
			fileArr[0]=photo_file;
			for (int i = 0; i < fileArr.length; i++) {
				String jsonStr = null;
				if(fileArr[i].getSize()>0) {
					String folder=null;
					switch (i) {
					case 0:
						folder="staff";
						break;
					}
					jsonStr = FileUploadUtils.appUploadFile(fileArr[i],folder);
					JSONObject fileJson = JSONObject.fromObject(jsonStr);
					if("成功".equals(fileJson.get("msg"))) {
						JSONObject dataJO = (JSONObject)fileJson.get("data");
						switch (i) {
						case 0:
							staff.setPhoto(dataJO.get("src").toString());
							break;
						}
					}
				}
			}
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			staff.setUuid(uuid);
			int count=staffService.add(staff);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "创建巡检人员成功！");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "创建巡检人员失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}
	
	@RequestMapping(value="/queryStaffList")
	@ResponseBody
	public Map<String, Object> queryStaffList(String name,Integer deptId, String secondDeptName,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = staffService.queryForInt(name,deptId, secondDeptName);
			List<Staff> staffList=staffService.queryList(name, deptId, secondDeptName, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", staffList);
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

	@RequestMapping(value="/getGJCKCanvasData")
	@ResponseBody
	public Map<String, Object> getGJCKCanvasData(Integer areaId, Integer tagId, String timeStart, String timeEnd) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Tracking> trackingList=trackingService.selectCanvasData(areaId,tagId,timeStart,timeEnd);
		
		if(trackingList.size()==0) {
			jsonMap.put("message", "no");
			jsonMap.put("info", "暂无轨迹！");
		}
		else {
			jsonMap.put("message", "ok");
			jsonMap.put("trackingList", trackingList);
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
	public Map<String, Object> getCenAnaData(Integer recently, Integer ptId, Integer staffId, String startDate, String endDate) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		int reachDayCount=linePatRecService.getReachDayCount(recently,ptId,staffId,startDate,endDate);
		int sumDayCount=patrolPlanService.getSumDayCount(recently,ptId,staffId,startDate,endDate);
		float lineReachPercent=linePatRecService.getReachPercent(recently,ptId,staffId,startDate,endDate);
		float areaReachPercent=areaPatRecService.getReachPercent(recently,ptId,staffId,startDate,endDate);
		
		List<String> lpXAxisDataList=patrolLineService.selectXAxisData(recently,ptId,staffId,startDate,endDate);
		List<Integer> lpSeriesDataList=new ArrayList<Integer>();
		List<LinePatRec> lpbcdList=linePatRecService.selectBarChartData(recently,ptId,staffId,startDate,endDate);
		for (int i = 0; i < lpXAxisDataList.size(); i++) {
			String xAxisName = lpXAxisDataList.get(i);
			for (int j = 0; j < lpbcdList.size(); j++) {
				LinePatRec lpbcdItem = lpbcdList.get(j);
				String plName = lpbcdItem.getPlName();
				if(xAxisName.equals(plName)) {
					Integer finishPercent = lpbcdItem.getFinishPercent();
					lpSeriesDataList.add(finishPercent);
				}
			}
		}
		
		List<String> apXAxisDataList=patrolAreaService.selectXAxisData(recently,ptId,staffId,startDate,endDate);
		List<Integer> apSeriesDataList=new ArrayList<Integer>();
		List<AreaPatRec> apbcdList=areaPatRecService.selectBarChartData(recently,ptId,staffId,startDate,endDate);
		for (int i = 0; i < apXAxisDataList.size(); i++) {
			String xAxisName = apXAxisDataList.get(i);
			for (int j = 0; j < apbcdList.size(); j++) {
				AreaPatRec apbcdItem = apbcdList.get(j);
				String paName = apbcdItem.getPaName();
				if(xAxisName.equals(paName)) {
					Integer finishPercent = apbcdItem.getFinishPercent();
					apSeriesDataList.add(finishPercent);
				}
			}
		}
		
		jsonMap.put("reachDayCount", reachDayCount);
		jsonMap.put("sumDayCount", sumDayCount);
		jsonMap.put("lineReachPercent", lineReachPercent);
		jsonMap.put("areaReachPercent", areaReachPercent);
		
		jsonMap.put("lpXAxisDataList", lpXAxisDataList);
		jsonMap.put("lpSeriesDataList", lpSeriesDataList);

		jsonMap.put("apXAxisDataList", apXAxisDataList);
		jsonMap.put("apSeriesDataList", apSeriesDataList);
		
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

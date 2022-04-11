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
import com.cqZnxj.util.*;

/**
 * 设备管理类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/deviceMgmt")
public class DeviceMgmtController {

	@Autowired
	private PatrolDeviceService patrolDeviceService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private PatrolDeviceAccountService patrolDeviceAccountService;
	@Autowired
	private PatrolDeviceParamService patrolDeviceParamService;
	public static final String MODULE_NAME="deviceMgmt";
	//http://localhost:8080/CqZnxj/deviceMgmt/type/list
	
	@RequestMapping(value="/dept/list")
	public String goDeptList(HttpServletRequest request) {
		
		return MODULE_NAME+"/dept/list";
	}

	@RequestMapping(value="/dept/detail")
	public String goDeptDetail(HttpServletRequest request) {
		
		String deptId = request.getParameter("deptId");
		Dept dept=deptService.selectByDeptId(deptId);
		request.setAttribute("dept", dept);
		
		return MODULE_NAME+"/dept/detail";
	}
	
	@RequestMapping(value="/device/new")
	public String goDeviceNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/device/new";
	}
	
	@RequestMapping(value="/device/edit")
	public String goDeviceEdit(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolDevice pd=patrolDeviceService.selectById(id);
		request.setAttribute("pd", pd);
		
		return MODULE_NAME+"/device/edit";
	}
	
	@RequestMapping(value="/device/list")
	public String goDeviceList(HttpServletRequest request) {
		
		return MODULE_NAME+"/device/list";
	}
	
	@RequestMapping(value="/device/detail")
	public String goDeviceDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolDevice pd=patrolDeviceService.selectById(id);
		request.setAttribute("pd", pd);
		
		return MODULE_NAME+"/device/detail";
	}
	
	@RequestMapping(value="/account/new")
	public String goAccountNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/account/new";
	}

	@RequestMapping(value="/account/edit")
	public String goAccountEdit(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		PatrolDeviceAccount pda=patrolDeviceAccountService.selectById(id);
		request.setAttribute("pda", pda);
		
		return MODULE_NAME+"/account/edit";
	}
	
	@RequestMapping(value="/account/list")
	public String goAccountList(HttpServletRequest request) {
		
		return MODULE_NAME+"/account/list";
	}

	@RequestMapping(value="/account/detail")
	public String goAccountDetail(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		PatrolDeviceAccount pda=patrolDeviceAccountService.selectById(id);
		request.setAttribute("pda", pda);
		
		return MODULE_NAME+"/account/detail";
	}
	
	@RequestMapping(value="/param/new")
	public String goParamNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/param/new";
	}

	@RequestMapping(value="/param/edit")
	public String goParamEdit(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		PatrolDeviceParam pdp=patrolDeviceParamService.selectById(id);
		request.setAttribute("pdp", pdp);
		
		return MODULE_NAME+"/param/edit";
	}
	
	@RequestMapping(value="/param/list")
	public String goParamList(HttpServletRequest request) {
		
		return MODULE_NAME+"/param/list";
	}

	@RequestMapping(value="/param/detail")
	public String goParamDetail(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		PatrolDeviceParam pdp=patrolDeviceParamService.selectById(id);
		request.setAttribute("pdp", pdp);
		
		return MODULE_NAME+"/param/detail";
	}
	
	@RequestMapping(value="/queryDeptList")
	@ResponseBody
	public Map<String, Object> queryDeptList(String deptName,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = deptService.queryForInt(deptName);
			List<Dept> deptList=deptService.queryList(deptName, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", deptList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/newDevice")
	@ResponseBody
	public Map<String, Object> newDevice(PatrolDevice pd) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceService.add(pd);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建设备成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建设备失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deleteDevice",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteDevice(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=patrolDeviceService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除设备失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除设备成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/editDevice")
	@ResponseBody
	public Map<String, Object> editDevice(PatrolDevice pd) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceService.edit(pd);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑设备成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑设备失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryDeviceList")
	@ResponseBody
	public Map<String, Object> queryDeviceList(String name,Integer deptId, String deptName,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceService.queryForInt(name,deptId, deptName);
			List<PatrolDevice> pdList=patrolDeviceService.queryList(name, deptId, deptName, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", pdList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/newAccount")
	@ResponseBody
	public Map<String, Object> newAccount(PatrolDeviceAccount pda) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		String no = pda.getNo();
		String fileName = no + ".jpg";
		String avaPath="/CqZnxj/upload/devAcc"+fileName;
		String path = "D:/resource/CqZnxj/devAcc";
        QrcodeUtil.createQrCode(no, path, fileName);
		
        pda.setQrcodeUrl(avaPath);
		int count=patrolDeviceAccountService.add(pda);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建设备台账成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建设备台账失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deleteAccount",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteAccount(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=patrolDeviceAccountService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除设备台账失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除设备台账成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/editAccount")
	@ResponseBody
	public Map<String, Object> editAccount(PatrolDeviceAccount pda) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceAccountService.edit(pda);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑设备台账成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑设备台账失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryAccountList")
	@ResponseBody
	public Map<String, Object> queryAccountList(String no,String pdName,Integer deptId, String deptName,String createTimeStart,String createTimeEnd,
			String startTimeStart,String startTimeEnd,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceAccountService.queryForInt(no,pdName,deptId,deptName,createTimeStart,createTimeEnd,startTimeStart,startTimeEnd);
			List<PatrolDeviceAccount> pdaList=patrolDeviceAccountService.queryList(no,pdName, deptId,deptName,createTimeStart,createTimeEnd,startTimeStart,startTimeEnd, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", pdaList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/newParam")
	@ResponseBody
	public Map<String, Object> newParam(PatrolDeviceParam pdp) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceParamService.add(pdp);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建设备参数成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建设备参数失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deleteParam",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteParam(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=patrolDeviceParamService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除设备参数失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除设备参数成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editParam")
	@ResponseBody
	public Map<String, Object> editParam(PatrolDeviceParam pdp) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceParamService.edit(pdp);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑设备参数成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑设备参数失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryParamList")
	@ResponseBody
	public Map<String, Object> queryParamList(Integer deptId, String deptName,String pdName,String pdaNo,String name,
			String createTimeStart,String createTimeEnd,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceParamService.queryForInt(deptId,deptName,pdName,pdaNo,name,createTimeStart,createTimeEnd);
			List<PatrolDeviceParam> pdpList=patrolDeviceParamService.queryList(deptId,deptName, pdName, pdaNo, name, createTimeStart, createTimeEnd, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", pdpList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}

	@RequestMapping(value="/checkNoIfExist",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String checkNoIfExist(String no) {
		boolean exist=patrolDeviceAccountService.checkNoIfExist(no);
		PlanResult plan=new PlanResult();
		String json;
		if(exist) {
			plan.setStatus(0);
			plan.setMsg("设备编号已存在");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/queryDeviceCBBList")
	@ResponseBody
	public Map<String, Object> queryDeviceCBBList(String deptId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolDevice> pdList=patrolDeviceService.queryCBBList(deptId);
		
		jsonMap.put("rows", pdList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryAccountCBBList")
	@ResponseBody
	public Map<String, Object> queryAccountCBBList(String deptId,String pdId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolDeviceAccount> pdaList=patrolDeviceAccountService.queryCBBList(deptId,pdId);
		
		jsonMap.put("rows", pdaList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryAreaAccCBBList")
	@ResponseBody
	public Map<String, Object> queryAreaAccCBBList(String deptId,String paId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolDeviceAccount> pdaList=patrolDeviceAccountService.queryAreaAccCBBList(deptId,paId);
		
		jsonMap.put("rows", pdaList);
		
		return jsonMap;
	}
}

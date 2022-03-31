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
	private PatrolDeviceTypeService patrolDeviceTypeService;
	@Autowired
	private PatrolDeviceAccountService patrolDeviceAccountService;
	@Autowired
	private PatrolDeviceParamService patrolDeviceParamService;
	public static final String MODULE_NAME="deviceMgmt";
	//http://localhost:8080/CqZnxj/deviceMgmt/type/list
	
	@RequestMapping(value="/type/new")
	public String goTypeNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/type/new";
	}

	@RequestMapping(value="/type/edit")
	public String goTypeEdit(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		PatrolDeviceType pdt=patrolDeviceTypeService.selectById(id);
		request.setAttribute("pdt", pdt);
		
		return MODULE_NAME+"/type/edit";
	}
	
	@RequestMapping(value="/type/list")
	public String goTypeList(HttpServletRequest request) {
		
		return MODULE_NAME+"/type/list";
	}

	@RequestMapping(value="/type/detail")
	public String goTypeDetail(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		PatrolDeviceType pdt=patrolDeviceTypeService.selectById(id);
		request.setAttribute("pdt", pdt);
		
		return MODULE_NAME+"/type/detail";
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
	
	@RequestMapping(value="/newType")
	@ResponseBody
	public Map<String, Object> newType(PatrolDeviceType pdt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceTypeService.add(pdt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建设备类型成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建设备类型失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/checkIfExistDeviceByPdtIds",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String checkIfExistDeviceByPdtIds(String pdtIds,String pdtNames) {
		//TODO 针对分类的动态进行实时调整更新
		List<PatrolDeviceType> pdtList=patrolDeviceService.checkIfExistByPdtIds(pdtIds,pdtNames);
		PlanResult plan=new PlanResult();
		String json;
		if(pdtList.size()>0) {
			plan.setStatus(1);
			StringBuilder msgSB=new StringBuilder();
			for (int i = 0; i < pdtList.size(); i++) {
				PatrolDeviceType pdt = pdtList.get(i);
				msgSB.append(",");
				msgSB.append(pdt.getName());
			}
			msgSB.append("类型下有设备，请先删除设备");
			String msgStr = msgSB.toString();
			plan.setMsg(msgStr.substring(1, msgStr.length()));
			plan.setData(pdtList);
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(0);
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/deleteType",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteType(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=patrolDeviceTypeService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除设备类型失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除设备类型成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editType")
	@ResponseBody
	public Map<String, Object> editType(PatrolDeviceType pdt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceTypeService.edit(pdt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑设备类型成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑设备类型失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryTypeList")
	@ResponseBody
	public Map<String, Object> queryTypeList(String name,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceTypeService.queryForInt(name);
			List<PatrolDeviceType> pdtList=patrolDeviceTypeService.queryList(name, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", pdtList);
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
	public Map<String, Object> queryDeviceList(String name,String pdtName,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceService.queryForInt(name,pdtName);
			List<PatrolDeviceType> pdList=patrolDeviceService.queryList(name, pdtName, page, rows, sort, order);
			
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
	public Map<String, Object> queryAccountList(String no,String pdName,String pdtName,String createTimeStart,String createTimeEnd,
			String startTimeStart,String startTimeEnd,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceAccountService.queryForInt(no,pdName,pdtName,createTimeStart,createTimeEnd,startTimeStart,startTimeEnd);
			List<PatrolDeviceAccount> pdaList=patrolDeviceAccountService.queryList(no,pdName, pdtName,createTimeStart,createTimeEnd,startTimeStart,startTimeEnd, page, rows, sort, order);
			
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
	public Map<String, Object> queryParamList(String pdtName,String pdName,String pdaNo,String name,
			String createTimeStart,String createTimeEnd,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceParamService.queryForInt(pdtName,pdName,pdaNo,name,createTimeStart,createTimeEnd);
			List<PatrolDeviceParam> pdpList=patrolDeviceParamService.queryList(pdtName, pdName, pdaNo, name, createTimeStart, createTimeEnd, page, rows, sort, order);
			
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
	
	@RequestMapping(value="/queryTypeCBBList")
	@ResponseBody
	public Map<String, Object> queryTypeCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolDeviceType> pdtList=patrolDeviceTypeService.queryCBBList();
		
		jsonMap.put("rows", pdtList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryDeviceCBBList")
	@ResponseBody
	public Map<String, Object> queryDeviceCBBList(String pdtId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolDevice> pdList=patrolDeviceService.queryCBBList(pdtId);
		
		jsonMap.put("rows", pdList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryAccountCBBList")
	@ResponseBody
	public Map<String, Object> queryAccountCBBList(String pdId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolDeviceAccount> pdaList=patrolDeviceAccountService.queryCBBList(pdId);
		
		jsonMap.put("rows", pdaList);
		
		return jsonMap;
	}
}

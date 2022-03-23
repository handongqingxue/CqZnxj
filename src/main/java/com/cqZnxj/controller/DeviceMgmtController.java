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
 * �豸������
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/deviceMgmt")
public class DeviceMgmtController {

	@Autowired
	private PatrolDeviceTypeService patrolDeviceTypeService;
	public static final String MODULE_NAME="deviceMgmt";
	//http://localhost:8080/CqZnxj/deviceMgmt/type/list
	
	@RequestMapping(value="/type/new")
	public String goTypeNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/type/new";
	}

	@RequestMapping(value="/type/edit")
	public String goTypeEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		PatrolDeviceType pdt=patrolDeviceTypeService.selectById(id);
		request.setAttribute("pdt", pdt);
		
		return MODULE_NAME+"/type/edit";
	}
	
	@RequestMapping(value="/type/list")
	public String goTypeList(HttpServletRequest request) {
		
		return MODULE_NAME+"/type/list";
	}
	
	@RequestMapping(value="/newType")
	@ResponseBody
	public Map<String, Object> newType(PatrolDeviceType pdt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceTypeService.add(pdt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�����豸���ͳɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�����豸����ʧ�ܣ�");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/editType")
	@ResponseBody
	public Map<String, Object> editType(PatrolDeviceType pdt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceTypeService.edit(pdt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�༭�豸���ͳɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�༭�豸����ʧ�ܣ�");
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
}
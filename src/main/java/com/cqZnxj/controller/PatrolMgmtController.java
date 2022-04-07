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
 * Ѳ�������
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
	
	@RequestMapping(value="/newArea")
	@ResponseBody
	public Map<String, Object> newArea(PatrolArea pa) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolAreaService.add(pa);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "����Ѳ������ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "����Ѳ������ʧ�ܣ�");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/editArea")
	@ResponseBody
	public Map<String, Object> editArea(PatrolArea pa) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolAreaService.edit(pa);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�༭Ѳ������ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�༭Ѳ������ʧ�ܣ�");
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

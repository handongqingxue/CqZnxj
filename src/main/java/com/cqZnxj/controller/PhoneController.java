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
@RequestMapping("/phone")
public class PhoneController {

	@Autowired
	private PatrolDeviceAccountService patrolDeviceAccountService;
	@Autowired
	private PatrolDeviceParamService patrolDeviceParamService;
	@Autowired
	private DevParPatRecService devParPatRecService;
	@Autowired
	private LinePatRecService linePatRecService;
	@Autowired
	private AreaPatRecService areaPatRecService;

	@RequestMapping(value="/getPDAQrcodeInfo")
	@ResponseBody
	public Map<String, Object> getPDAQrcodeInfo(String pdaNo) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		PatrolDeviceAccount pda=patrolDeviceAccountService.getQrcodeInfoByNo(pdaNo);
		List<PatrolDeviceParam> pdpList=patrolDeviceParamService.selectPhListByPdaId(pda.getId());
		
		jsonMap.put("pda", pda);
		jsonMap.put("pdpList", pdpList);
		
		return jsonMap;
	}

	@RequestMapping(value="/getPDPInfo")
	@ResponseBody
	public Map<String, Object> getPDPInfo(Integer id,Integer ptId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		PatrolDeviceParam pdp=patrolDeviceParamService.selectPhInfoById(id);
		DevParPatRec dppr=devParPatRecService.selectByPdpIdPtId(pdp.getId(),ptId);
		
		jsonMap.put("pdp", pdp);
		jsonMap.put("dppr", dppr);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/saveDevParPatRec")
	@ResponseBody
	public Map<String, Object> saveDevParPatRec(DevParPatRec dppr) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count;
		boolean bool;
		Integer plId = dppr.getPlId();
		Integer ptId = dppr.getPtId();
		bool=linePatRecService.checkIfExist(plId,ptId);
		if(!bool) {
			LinePatRec lpr=new LinePatRec();
			lpr.setPlId(dppr.getPlId());
			lpr.setPtId(dppr.getPtId());
			linePatRecService.add(lpr);
		}
		
		Integer paId = dppr.getPaId();
		bool=areaPatRecService.checkIfExist(paId,ptId);
		if(!bool) {
			int lprId = linePatRecService.getIdByPlIdPtId(plId,ptId);
			
			AreaPatRec apr=new AreaPatRec();
			apr.setPlId(dppr.getPlId());
			apr.setPaId(dppr.getPaId());
			apr.setPtId(dppr.getPtId());
			apr.setLprId(lprId);
			count=areaPatRecService.add(apr);
		}
		
		bool=devParPatRecService.checkIfExist(dppr.getPdpId(),ptId);
		if(bool)
			count=devParPatRecService.editByPdpIdPtId(dppr);
		else
			count=devParPatRecService.add(dppr);
			
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "保存设备参数巡检记录成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "保存设备参数巡检记录失败！");
		}
		return jsonMap;
	}
}

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
	public Map<String, Object> getPDPInfo(Integer id) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		PatrolDeviceParam pdp=patrolDeviceParamService.selectPhInfoById(id);
		
		jsonMap.put("pdp", pdp);
		
		return jsonMap;
	}
}

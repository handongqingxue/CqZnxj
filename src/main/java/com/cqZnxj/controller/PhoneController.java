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

	@RequestMapping(value="/getPDAQrcodeInfo")
	@ResponseBody
	public Map<String, Object> getPDAQrcodeInfo(String pdaNo) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		PatrolDeviceAccount pda=patrolDeviceAccountService.getQrcodeInfoByNo(pdaNo);
		
		jsonMap.put("pda", pda);
		
		return jsonMap;
	}
}

package com.cqZnxj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 设备管理类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/deviceMgmt")
public class DeviceMgmtController {

	public static final String MODULE_NAME="deviceMgmt";
	//http://localhost:8080/CqZnxj/deviceMgmt/type/list
	
	@RequestMapping(value="/type/list")
	public String goTypeList(HttpServletRequest request) {
		
		return MODULE_NAME+"/type/list";
	}
}

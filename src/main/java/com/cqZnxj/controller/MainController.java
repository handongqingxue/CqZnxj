package com.cqZnxj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;
import com.cqZnxj.util.*;

@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private DeptService deptService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private UserService userService;
	@Autowired
	private TrackingService trackingService;
	
	/**
	 * 跳转到登录页
	 * @return
	 */
	@RequestMapping(value="/goLogin")
	public String goLogin() {
		
		return "login";
	}
	
	/**
	 * 跳转到注册页
	 * @return
	 */
	@RequestMapping(value="/goRegist")
	public String goRegist() {
		
		return "regist";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST,produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String login(String userName,String password,HttpServletRequest request) {
		System.out.println("===登录接口===");
		//返回值的json
		PlanResult plan=new PlanResult();
		HttpSession session=request.getSession();
		//TODO在这附近添加登录储存信息步骤，将用户的账号以及密码储存到shiro框架的管理配置当中方便后续查询
		try {
			System.out.println("验证码一致");
			UsernamePasswordToken token = new UsernamePasswordToken(userName,password);  
			Subject currentUser = SecurityUtils.getSubject();  
			if (!currentUser.isAuthenticated()){
				//使用shiro来验证  
				token.setRememberMe(true);  
				currentUser.login(token);//验证角色和权限  
			}
		}catch (Exception e) {
			e.printStackTrace();
			plan.setStatus(1);
			plan.setMsg("登陆失败");
			return JsonUtil.getJsonFromObject(plan);
		}
		User msg=(User)SecurityUtils.getSubject().getPrincipal();
		session.setAttribute("user", msg);
		
		plan.setStatus(0);
		plan.setMsg("验证通过");
		plan.setUrl("/deviceMgmt/dept/list?nav=bmcx");
		return JsonUtil.getJsonFromObject(plan);
	}
	
	/**
	 * 注册信息处理接口
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/regist" , method = RequestMethod.POST,produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String regist(User user, HttpServletRequest request) {
		
		PlanResult plan=new PlanResult();
		int count=userService.add(user);
		if(count>0) {
			plan.setStatus(1);
			plan.setMsg("注册成功");
			plan.setData(user);
			plan.setUrl("/main/goLogin");
		}
		else {
			plan.setStatus(0);
			plan.setMsg("注册失败");
		}
		
		return JsonUtil.getJsonFromObject(plan);
	}
	
	//https://blog.csdn.net/qq_19642249/article/details/53845315?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-53845315-blog-114814468.pc_relevant_multi_platform_whitelistv1&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-53845315-blog-114814468.pc_relevant_multi_platform_whitelistv1&utm_relevant_index=1
	//https://blog.csdn.net/weixin_35728532/article/details/116159700
	@RequestMapping(value="/goBaiDuMapTest")
	public String goBaiDuMapTest() {
		
		float areaLongitudeStart=(float)112.380482;
		float areaLongitudeEnd=(float)120.380482;
		float areaLatitudeStart=(float)36.87649;
		float areaLatitudeEnd=(float)41.87649;
		float mapWidth=500;
		float mapHeight=300;
		List<Tracking> trackingList = trackingService.selectCanvasData(1, 2, null, null);
		for (int i = 0; i < trackingList.size(); i++) {
			Tracking tracking = trackingList.get(i);
			float x = Float.valueOf(tracking.getX());
			float y = Float.valueOf(tracking.getY());
			System.out.println("x==="+x);
			System.out.println("y==="+y);
		}
		
		return "baiDuMapTest";
	}

	@RequestMapping(value="/queryDeptTreeList")
	@ResponseBody
	public List<TreeNode> queryDeptTreeList() {

		List<TreeNode> deptList=deptService.queryTreeList(0);
		
		return deptList;
	}
	
	@RequestMapping(value="/queryDeptCBBList")
	@ResponseBody
	public Map<String, Object> queryDeptCBBList(Integer parentId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Dept> deptList=deptService.queryCBBList(parentId);
		
		jsonMap.put("rows", deptList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryStaffCBBList")
	@ResponseBody
	public Map<String, Object> queryStaffCBBList(String deptId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Staff> staffList=staffService.queryCBBList(deptId);
		
		jsonMap.put("rows", staffList);
		
		return jsonMap;
	}
}

package com.cqZnxj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;
import com.cqZnxj.util.FileUploadUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/phone")
public class PhoneController {

	//小程序上传图片:https://blog.csdn.net/zhaoyazhi2129/article/details/53926507/?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1.pc_relevant_antiscanv2&spm=1001.2101.3001.4242.2&utm_relevant_index=4
	//多张图片上传:https://blog.csdn.net/weixin_52309490/article/details/118421189
	//小程序录制视频:https://blog.csdn.net/Cris_are/article/details/108751409
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
	@Autowired
	private DevAccPatRecService devAccPatRecService;
	@Autowired
	private PatrolLineService patrolLineService;
	@Autowired
	private PatrolAreaService patrolAreaService;
	@Autowired
	private PatLineAreaAccSetService patLineAreaAccSetService;

	@RequestMapping(value="/getPLTotalInfo")
	@ResponseBody
	public Map<String, Object> getPLTotalInfo() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolLine> plList=patrolLineService.getTotalInfo();
		List<LinePatRec> lprList=linePatRecService.getTodayList();
		int todayFinishCount=0;
		for (LinePatRec lpr : lprList) {
			if(lpr.getFinish())
				todayFinishCount++;
		}
		
		int jrxjwcl=0;
		int lprListSize=lprList.size();
		if(lprListSize>0)
			jrxjwcl=todayFinishCount/lprListSize*100;
		jsonMap.put("plList", plList);
		jsonMap.put("jrxjwcl", jrxjwcl);
		
		return jsonMap;
	}

	@RequestMapping(value="/getPAList")
	@ResponseBody
	public Map<String, Object> getPAList(Integer plId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		PatrolLine pl=patrolLineService.selectById(plId);
		List<PatrolArea> paList=patrolAreaService.selectPhListByPlId(plId);
		
		jsonMap.put("plName", pl.getName());
		jsonMap.put("paList", paList);
		
		return jsonMap;
	}

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
		if(dppr==null)
			dppr=new DevParPatRec();
		
		jsonMap.put("pdp", pdp);
		jsonMap.put("dppr", dppr);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/saveDevParPatRec")
	@ResponseBody
	public Map<String, Object> saveDevParPatRec(@RequestParam(value="photo_file",required=false) MultipartFile photo_file,DevParPatRec dppr) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count;
		boolean bool;
		Integer plId = dppr.getPlId();
		Integer ptId = dppr.getPtId();
		int lprId=0;
		int aprId=0;
		int daprId;
		bool=linePatRecService.checkIfExist(plId,ptId);
		if(!bool) {
			int patAreaCount=patLineAreaAccSetService.getAreaCountByPlId(plId);
			
			LinePatRec lpr=new LinePatRec();
			lpr.setPlId(dppr.getPlId());
			lpr.setPtId(dppr.getPtId());
			lpr.setPatAreaCount(patAreaCount);
			lpr.setStartTime(dppr.getStartTime());
			linePatRecService.add(lpr);
		}
		lprId = linePatRecService.getIdByPlIdPtId(plId,ptId);
		
		Integer paId = dppr.getPaId();
		bool=areaPatRecService.checkIfExist(paId,ptId);
		if(!bool) {
			int patAccCount=patLineAreaAccSetService.getAccCountByPlIdPaId(plId,paId);
			
			AreaPatRec apr=new AreaPatRec();
			apr.setPatAccCount(patAccCount);
			apr.setPlId(dppr.getPlId());
			apr.setPaId(dppr.getPaId());
			apr.setPtId(dppr.getPtId());
			apr.setLprId(lprId);
			apr.setStartTime(apr.getStartTime());
			count=areaPatRecService.add(apr);
		}
		aprId = areaPatRecService.getIdByPaIdPtId(paId,ptId);

		Integer pdaId = dppr.getPdaId();
		bool=devAccPatRecService.checkIfExist(pdaId,ptId);
		if(!bool) {
			int patParCount=patrolDeviceParamService.getCountByPdaId(pdaId);
			
			DevAccPatRec dapr=new DevAccPatRec();
			dapr.setPatParCount(patParCount);
			dapr.setPlId(dppr.getPlId());
			dapr.setPaId(dppr.getPaId());
			dapr.setPdaId(dppr.getPdaId());
			dapr.setPtId(dppr.getPtId());
			dapr.setPsId(dppr.getPsId());
			dapr.setAprId(aprId);
			dapr.setStartTime(dppr.getStartTime());
			count=devAccPatRecService.add(dapr);
		}
		daprId=devAccPatRecService.getIdByPdaIdPtId(pdaId,ptId);
		
		bool=devParPatRecService.checkIfExist(dppr.getPdpId(),ptId);
		if(bool)
			count=devParPatRecService.editByPdpIdPtId(dppr);
		else {
			
			dppr.setDaprId(daprId);
			count=devParPatRecService.add(dppr);
			if(count>0)
				count=devAccPatRecService.updateFinishCountById(daprId,aprId,lprId);
		}
			
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

	@RequestMapping(value="/uploadFile")
	@ResponseBody
	public void upload(@RequestParam(value="file",required=false) MultipartFile file, DevParPatRec dppr) {
		
		try {
			System.out.println("Size==="+file.getSize());
			System.out.println("PtId==="+dppr.getPtId());
			System.out.println("PpdId==="+dppr.getPdpId());
			if(file!=null) {
				 if(file.getSize()>0) {
					String jsonStr = FileUploadUtils.appUploadFile(file,"devPar/file/");
					JSONObject fileJson = JSONObject.fromObject(jsonStr);
					if("成功".equals(fileJson.get("msg"))) {
						JSONObject dataJO = (JSONObject)fileJson.get("data");
						String src = dataJO.get("src").toString();
						switch (dppr.getFileNum()) {
						case 1:
							dppr.setPhotoUrl1(src);
							break;
						case 2:
							dppr.setPhotoUrl2(src);
							break;
						case 3:
							dppr.setPhotoUrl3(src);
							break;
						case 4:
							dppr.setVideoUrl1(src);
							break;
						}
						devParPatRecService.updateFileUrlByPdpIdPtId(dppr);
					}
				 }
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

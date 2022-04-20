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

		Integer pdaId = dppr.getPdaId();
		bool=devAccPatRecService.checkIfExist(pdaId,ptId);
		if(!bool) {
			int aprId = areaPatRecService.getIdByPaIdPtId(paId,ptId);
			
			DevAccPatRec dapr=new DevAccPatRec();
			dapr.setPlId(dppr.getPlId());
			dapr.setPaId(dppr.getPaId());
			dapr.setPdaId(dppr.getPdaId());
			dapr.setPtId(dppr.getPtId());
			dapr.setPsId(dppr.getPsId());
			dapr.setAprId(aprId);
			count=devAccPatRecService.add(dapr);
		}
		
		bool=devParPatRecService.checkIfExist(dppr.getPdpId(),ptId);
		if(bool)
			count=devParPatRecService.editByPdpIdPtId(dppr);
		else {
			int daprId=devAccPatRecService.getIdByPdaIdPtId(pdaId,ptId);
			
			dppr.setDaprId(daprId);
			count=devParPatRecService.add(dppr);
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

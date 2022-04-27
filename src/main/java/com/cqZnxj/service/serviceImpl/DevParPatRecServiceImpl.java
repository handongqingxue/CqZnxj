package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class DevParPatRecServiceImpl implements DevParPatRecService {

	@Autowired
	private DevParPatRecMapper devParPatRecDao;

	@Override
	public int add(DevParPatRec dppr) {
		// TODO Auto-generated method stub
		return devParPatRecDao.add(dppr);
	}

	@Override
	public int editByPdpIdPtId(DevParPatRec dppr) {
		// TODO Auto-generated method stub
		return devParPatRecDao.editByPdpIdPtId(dppr);
	}

	@Override
	public DevParPatRec selectByPdpIdPtId(Integer pdpId, Integer ptId) {
		// TODO Auto-generated method stub
		return devParPatRecDao.selectByPdpIdPtId(pdpId,ptId);
	}

	@Override
	public boolean checkIfExist(Integer pdpId, Integer ptId) {
		// TODO Auto-generated method stub
		return devParPatRecDao.getCount(pdpId,ptId)==0?false:true;
	}

	@Override
	public int updateFileUrlByPdpIdPtId(DevParPatRec dppr) {
		// TODO Auto-generated method stub
		return devParPatRecDao.updateFileUrlByPdpIdPtId(dppr);
	}

	@Override
	public int queryForInt(String plName, String paName, String pdName, String pdaNo, String pdpName,
			 String pdpUnit, Integer pdLevel, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return devParPatRecDao.queryForInt(plName, paName, pdName, pdaNo, pdpName, pdpUnit, pdLevel, startTime, endTime);
	}

	@Override
	public List<DevParPatRec> queryList(String plName, String paName, String pdName, String pdaNo, String pdpName,
			 String pdpUnit, Integer pdLevel, String startTime, String endTime, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return devParPatRecDao.queryList(plName, paName, pdName, pdaNo, pdpName, pdpUnit, pdLevel,
				startTime, endTime, (page-1)*rows, rows, sort, order);
	}
}

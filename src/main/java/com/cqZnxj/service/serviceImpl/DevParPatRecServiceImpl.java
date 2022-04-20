package com.cqZnxj.service.serviceImpl;

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
}

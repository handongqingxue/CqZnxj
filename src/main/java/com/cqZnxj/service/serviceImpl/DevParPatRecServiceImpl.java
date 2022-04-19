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
	public int save(DevParPatRec dppr) {
		// TODO Auto-generated method stub
		int count;
		if(devParPatRecDao.getCount(dppr.getPdpId(),dppr.getPtId())==0)
			count=devParPatRecDao.add(dppr);
		else
			count=devParPatRecDao.editByPdpIdPtId(dppr);
		return count;
	}

	@Override
	public DevParPatRec selectByPdpIdPtId(Integer pdpId, Integer ptId) {
		// TODO Auto-generated method stub
		return devParPatRecDao.selectByPdpIdPtId(pdpId,ptId);
	}
}

package com.cqZnxj.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class DevAccPatRecServiceImpl implements DevAccPatRecService {

	@Autowired
	private DevAccPatRecMapper devAccPatRecDao;

	@Override
	public int add(DevAccPatRec dapr) {
		// TODO Auto-generated method stub
		return devAccPatRecDao.add(dapr);
	}

	@Override
	public boolean checkIfExist(Integer pdaId, Integer ptId) {
		// TODO Auto-generated method stub
		return devAccPatRecDao.getCount(pdaId,ptId)==0?false:true;
	}

	@Override
	public int getIdByPdaIdPtId(Integer pdaId, Integer ptId) {
		// TODO Auto-generated method stub
		return devAccPatRecDao.getIdByPdaIdPtId(pdaId, ptId);
	}
}

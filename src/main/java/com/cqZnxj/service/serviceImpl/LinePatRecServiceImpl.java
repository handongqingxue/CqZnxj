package com.cqZnxj.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class LinePatRecServiceImpl implements LinePatRecService {

	@Autowired
	private LinePatRecMapper linePatRecDao;

	@Override
	public int add(LinePatRec lpr) {
		// TODO Auto-generated method stub
		return linePatRecDao.add(lpr);
	}

	@Override
	public boolean checkIfExist(Integer plId, Integer ptId) {
		// TODO Auto-generated method stub
		return linePatRecDao.getCount(plId,ptId)==0?false:true;
	}

	@Override
	public int getIdByPlIdPtId(Integer plId, Integer ptId) {
		// TODO Auto-generated method stub
		return linePatRecDao.getIdByPlIdPtId(plId, ptId);
	}
}

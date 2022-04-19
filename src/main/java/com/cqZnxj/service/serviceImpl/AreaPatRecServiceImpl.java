package com.cqZnxj.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.AreaPatRec;
import com.cqZnxj.service.*;

@Service
public class AreaPatRecServiceImpl implements AreaPatRecService {

	@Autowired
	private AreaPatRecMapper areaPatRecDao;

	@Override
	public int add(AreaPatRec apr) {
		// TODO Auto-generated method stub
		return areaPatRecDao.add(apr);
	}

	@Override
	public boolean checkIfExist(Integer paId, Integer ptId) {
		// TODO Auto-generated method stub
		return areaPatRecDao.getCount(paId,ptId)==0?false:true;
	}
}

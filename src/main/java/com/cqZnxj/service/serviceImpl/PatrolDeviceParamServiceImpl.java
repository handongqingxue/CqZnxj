package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatrolDeviceParamServiceImpl implements PatrolDeviceParamService {

	@Autowired
	private PatrolDeviceParamMapper patrolDeviceParamDao;

	@Override
	public int queryForInt(String pdtName, String pdName, String pdaNo, String name, String createTimeStart, String createTimeEnd) {
		// TODO Auto-generated method stub
		return patrolDeviceParamDao.queryForInt(pdtName, pdName, pdaNo, name, createTimeStart, createTimeEnd);
	}

	@Override
	public List<PatrolDeviceParam> queryList(String pdtName, String pdName, String pdaNo, String name, 
			String createTimeStart, String createTimeEnd, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return patrolDeviceParamDao.queryList(pdtName, pdName, pdaNo, name, createTimeStart, createTimeEnd, (page-1)*rows, rows, sort, order);
	}
}

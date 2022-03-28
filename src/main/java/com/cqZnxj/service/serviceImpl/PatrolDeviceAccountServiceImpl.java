package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.PatrolDeviceAccount;
import com.cqZnxj.service.*;

@Service
public class PatrolDeviceAccountServiceImpl implements PatrolDeviceAccountService {

	@Autowired
	private PatrolDeviceAccountMapper patrolDeviceAccountDao;

	public int queryForInt(String deviceNo, String pdName, String pdtName, String createTimeStart,
			String createTimeEnd, String startTimeStart, String startTimeEnd) {
		// TODO Auto-generated method stub
		return patrolDeviceAccountDao.queryForInt(deviceNo, pdName, pdtName, createTimeStart,
				createTimeEnd, startTimeStart, startTimeEnd);
	}

	public List<PatrolDeviceAccount> queryList(String deviceNo, String pdName, String pdtName,
			String createTimeStart, String createTimeEnd, String startTimeStart, String startTimeEnd, int page,
			int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return patrolDeviceAccountDao.queryList(deviceNo, pdName, pdtName, createTimeStart, createTimeEnd, 
				startTimeStart, startTimeEnd, (page-1)*rows, rows, sort, order);
	}
}

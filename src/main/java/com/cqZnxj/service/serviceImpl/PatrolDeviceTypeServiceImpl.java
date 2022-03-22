package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.service.*;
import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;

@Service
public class PatrolDeviceTypeServiceImpl implements PatrolDeviceTypeService {

	@Autowired
	private PatrolDeviceTypeMapper patrolDeviceTypeDao;

	public int queryForInt(String mc) {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.queryForInt(mc);
	}

	public List<PatrolDeviceType> queryList(String mc, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.queryList(mc, (page-1)*rows, rows, sort, order);
	}
}

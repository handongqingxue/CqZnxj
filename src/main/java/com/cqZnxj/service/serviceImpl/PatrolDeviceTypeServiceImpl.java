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

	public int add(PatrolDeviceType pdt) {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.add(pdt);
	}

	public int queryForInt(String name) {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.queryForInt(name);
	}

	public List<PatrolDeviceType> queryList(String name, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.queryList(name, (page-1)*rows, rows, sort, order);
	}
}

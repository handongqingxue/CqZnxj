package com.cqZnxj.service.serviceImpl;

import java.util.Arrays;
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

	public int edit(PatrolDeviceType pdt) {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.edit(pdt);
	}

	public int queryForInt(String name) {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.queryForInt(name);
	}

	public List<PatrolDeviceType> queryList(String name, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.queryList(name, (page-1)*rows, rows, sort, order);
	}

	public PatrolDeviceType selectById(String id) {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.selectById(id);
	}

	public List<PatrolDeviceType> queryCBBList() {
		// TODO Auto-generated method stub
		return patrolDeviceTypeDao.queryCBBList();
	}

	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=patrolDeviceTypeDao.deleteByIds(idList);
		return count;
	}
}

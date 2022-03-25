package com.cqZnxj.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatrolDeviceServiceImpl implements PatrolDeviceService {

	@Autowired
	private PatrolDeviceMapper patrolDeviceDao;

	public int add(PatrolDevice pd) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.add(pd);
	}

	public int queryForInt(String name, String pdtName) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.queryForInt(name, pdtName);
	}

	public List<PatrolDeviceType> queryList(String name, String pdtName, int page, int rows, String sort,
			String order) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.queryList(name, pdtName, (page-1)*rows, rows, sort, order);
	}

	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=patrolDeviceDao.deleteByIds(idList);
		return count;
	}

	public PatrolDevice selectById(String id) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.selectById(id);
	}

	public int edit(PatrolDevice pd) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.edit(pd);
	}
}
package com.cqZnxj.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
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
	public int queryForInt(Integer deptId, String secondDeptName, String pdName, String pdaNo, String name, String createTimeStart, String createTimeEnd) {
		// TODO Auto-generated method stub
		return patrolDeviceParamDao.queryForInt(deptId, secondDeptName, pdName, pdaNo, name, createTimeStart, createTimeEnd);
	}

	@Override
	public List<PatrolDeviceParam> queryList(Integer deptId, String secondDeptName, String pdName, String pdaNo, String name, 
			String createTimeStart, String createTimeEnd, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return patrolDeviceParamDao.queryList(deptId, secondDeptName, pdName, pdaNo, name, createTimeStart, createTimeEnd, (page-1)*rows, rows, sort, order);
	}

	@Override
	public int add(PatrolDeviceParam pdp) {
		// TODO Auto-generated method stub
		return patrolDeviceParamDao.add(pdp);
	}

	@Override
	public PatrolDeviceParam selectById(String id) {
		// TODO Auto-generated method stub
		return patrolDeviceParamDao.selectById(id);
	}

	@Override
	public int edit(PatrolDeviceParam pdp) {
		// TODO Auto-generated method stub
		return patrolDeviceParamDao.edit(pdp);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=patrolDeviceParamDao.deleteByIds(idList);
		return count;
	}

	@Override
	public List<PatrolDeviceParam> selectPhListByPdaId(Integer pdaId) {
		// TODO Auto-generated method stub
		return patrolDeviceParamDao.selectPhListByPdaId(pdaId);
	}

	@Override
	public PatrolDeviceParam selectPhInfoById(Integer id) {
		// TODO Auto-generated method stub
		return patrolDeviceParamDao.selectPhInfoById(id);
	}

	@Override
	public int getCountByPdaId(Integer pdaId) {
		// TODO Auto-generated method stub
		List<String> pdaIdList = new ArrayList<String>();
		pdaIdList.add(pdaId+"");
		return patrolDeviceParamDao.getCountByPdaIdList(pdaIdList);
	}
}

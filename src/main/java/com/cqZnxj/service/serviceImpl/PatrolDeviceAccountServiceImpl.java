package com.cqZnxj.service.serviceImpl;

import java.util.Arrays;
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
	@Autowired
	private PatrolDeviceParamMapper patrolDeviceParamDao;
	@Autowired
	private PatrolAreaMapper patrolAreaDao;

	public int queryForInt(String no, String pdName, Integer deptId, String deptName, String createTimeStart,
			String createTimeEnd, String startTimeStart, String startTimeEnd) {
		// TODO Auto-generated method stub
		return patrolDeviceAccountDao.queryForInt(no, pdName, deptId, deptName, createTimeStart,
				createTimeEnd, startTimeStart, startTimeEnd);
	}

	public List<PatrolDeviceAccount> queryList(String no, String pdName, Integer deptId,String deptName,
			String createTimeStart, String createTimeEnd, String startTimeStart, String startTimeEnd, int page,
			int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return patrolDeviceAccountDao.queryList(no, pdName, deptId, deptName, createTimeStart, createTimeEnd, 
				startTimeStart, startTimeEnd, (page-1)*rows, rows, sort, order);
	}

	@Override
	public int add(PatrolDeviceAccount pda) {
		// TODO Auto-generated method stub
		return patrolDeviceAccountDao.add(pda);
	}

	@Override
	public boolean checkNoIfExist(String no) {
		// TODO Auto-generated method stub
		int count=patrolDeviceAccountDao.getCountByNo(no);
		return count==0?false:true;
	}

	@Override
	public PatrolDeviceAccount selectById(String id) {
		// TODO Auto-generated method stub
		return patrolDeviceAccountDao.selectById(id);
	}

	@Override
	public int edit(PatrolDeviceAccount pda) {
		// TODO Auto-generated method stub
		return patrolDeviceAccountDao.edit(pda);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=patrolDeviceAccountDao.deleteByIds(idList);
		if(patrolDeviceParamDao.getCountByPdaIdList(idList)>0)
			patrolDeviceParamDao.deleteByPdaIdList(idList);
		return count;
	}

	@Override
	public List<PatrolDeviceAccount> queryCBBList(String deptId,String pdId) {
		// TODO Auto-generated method stub
		return patrolDeviceAccountDao.queryCBBList(deptId,pdId);
	}

	@Override
	public List<PatrolDeviceAccount> queryAreaAccCBBList(String deptId, String paId) {
		// TODO Auto-generated method stub
		String pdaIds=patrolAreaDao.getPdaIdsById(paId);
		String[] pdaIdArr = pdaIds.split(",");
		List<String> pdaIdList = Arrays.asList(pdaIdArr);
		return patrolDeviceAccountDao.queryCBBListByIdList(pdaIdList);
	}
}

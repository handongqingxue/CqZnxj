package com.cqZnxj.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffMapper staffDao;

	@Override
	public int add(Staff staff) {
		// TODO Auto-generated method stub
		return staffDao.add(staff);
	}

	@Override
	public int deleteByUuids(String uuids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> uuidList = Arrays.asList(uuids.split(","));
		count=staffDao.deleteByUuids(uuidList);
		return count;
	}

	@Override
	public int edit(Staff staff) {
		// TODO Auto-generated method stub
		return staffDao.edit(staff);
	}

	@Override
	public List<Staff> queryCBBList(String deptId) {
		// TODO Auto-generated method stub
		return staffDao.queryCBBList(deptId);
	}

	@Override
	public List<Staff> queryCBBListByPtId(Integer ptId) {
		// TODO Auto-generated method stub
		return staffDao.queryCBBListByPtId(ptId);
	}

	@Override
	public Staff selectByJobNumber(String jobNumber) {
		// TODO Auto-generated method stub
		return staffDao.selectByJobNumber(jobNumber);
	}

	@Override
	public int queryForInt(String name, Integer deptId, String secondDeptName) {
		// TODO Auto-generated method stub
		return staffDao.queryForInt(name, deptId, secondDeptName);
	}

	@Override
	public List<Staff> queryList(String name, Integer deptId, String secondDeptName, int page, int rows, String sort,
			String order) {
		// TODO Auto-generated method stub
		return staffDao.queryList(name, deptId, secondDeptName, (page-1)*rows, rows, sort, order);
	}

	@Override
	public Staff selectByUuid(String uuid) {
		// TODO Auto-generated method stub
		return staffDao.selectByUuid(uuid);
	}
}

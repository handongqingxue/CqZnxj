package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface StaffService {

	int add(Staff staff);

	List<Staff> queryCBBList(String deptId);

	List<Staff> queryCBBListByPtId(Integer ptId);

	Staff selectByJobNumber(String jobNumber);

	int queryForInt(String name, Integer deptId, String secondDeptName);

	List<Staff> queryList(String name, Integer deptId, String secondDeptName, int page, int rows, String sort,
			String order);

}

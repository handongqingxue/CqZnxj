package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolDeviceService {

	int add(PatrolDevice pd);

	int queryForInt(String name, Integer deptId, String secondDeptName);

	List<PatrolDevice> queryList(String name, Integer deptId, String secondDeptName, int page, int rows, String sort, String order);

	int deleteByIds(String ids);

	int edit(PatrolDevice pd);

	PatrolDevice selectById(String id);

	List<PatrolDevice> queryCBBList(String deptId);

}

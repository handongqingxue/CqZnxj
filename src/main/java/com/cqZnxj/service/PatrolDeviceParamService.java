package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.PatrolDeviceParam;

public interface PatrolDeviceParamService {

	int queryForInt(Integer deptId, String deptName, String pdName, String pdaNo, String name, String createTimeStart, String createTimeEnd);

	List<PatrolDeviceParam> queryList(Integer deptId, String deptName, String pdName, String pdaNo, String name, 
			String createTimeStart, String createTimeEnd, int page, int rows, String sort, String order);

	int add(PatrolDeviceParam pdp);

	PatrolDeviceParam selectById(String id);

	int edit(PatrolDeviceParam pdp);

	int deleteByIds(String ids);

	List<PatrolDeviceParam> selectPhListByPdaId(Integer pdaId);

	PatrolDeviceParam selectPhInfoById(Integer id);

}

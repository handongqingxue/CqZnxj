package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.PatrolDeviceParam;

public interface PatrolDeviceParamService {

	int queryForInt(String pdtName, String pdName, String pdaNo, String name, String createTimeStart, String createTimeEnd);

	List<PatrolDeviceParam> queryList(String pdtName, String pdName, String pdaNo, String name, 
			String createTimeStart, String createTimeEnd, int page, int rows, String sort, String order);

	int add(PatrolDeviceParam pdp);

	PatrolDeviceParam selectById(String id);

	int edit(PatrolDeviceParam pdp);

}

package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.PatrolDeviceAccount;

public interface PatrolDeviceAccountService {

	int queryForInt(String deviceNo, String pdName, String pdtName, String createTimeStart,
			String createTimeEnd, String startTimeStart, String startTimeEnd);

	List<PatrolDeviceAccount> queryList(String deviceNo, String pdName, String pdtName,
			String createTimeStart, String createTimeEnd, String startTimeStart, String startTimeEnd, int page,
			int rows, String sort, String order);

}

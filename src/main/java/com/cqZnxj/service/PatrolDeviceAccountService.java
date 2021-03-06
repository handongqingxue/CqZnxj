package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.PatrolDeviceAccount;

public interface PatrolDeviceAccountService {

	int queryForInt(String no, String pdName, Integer deptId, String secondDeptName, String createTimeStart,
			String createTimeEnd, String startTimeStart, String startTimeEnd);

	List<PatrolDeviceAccount> queryList(String no, String pdName, Integer deptId,String secondDeptName,
			String createTimeStart, String createTimeEnd, String startTimeStart, String startTimeEnd, int page,
			int rows, String sort, String order);

	int add(PatrolDeviceAccount pda);

	boolean checkNoIfExist(String no);

	PatrolDeviceAccount selectById(String id);

	int edit(PatrolDeviceAccount pda);

	int deleteByIds(String ids);

	List<PatrolDeviceAccount> queryCBBList(String deptId,String pdId);

	List<PatrolDeviceAccount> queryAreaAccCBBList(String deptId, String paId);

	PatrolDeviceAccount getQrcodeInfoByNo(String no);

}

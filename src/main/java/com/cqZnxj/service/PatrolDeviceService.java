package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolDeviceService {

	int add(PatrolDevice pd);

	int queryForInt(String name, String pdtName);

	List<PatrolDeviceType> queryList(String name, String pdtName, int page, int rows, String sort, String order);

	int deleteByIds(String ids);

	int edit(PatrolDevice pd);

	PatrolDevice selectById(String id);

	List<PatrolDeviceType> checkIfExistByPdtIds(String pdtIds, String pdtNames);

	List<PatrolDevice> queryCBBList(String pdtId);

}

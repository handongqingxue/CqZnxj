package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolDeviceTypeService {

	int add(PatrolDeviceType pdt);

	int edit(PatrolDeviceType pdt);

	int queryForInt(String name);

	List<PatrolDeviceType> queryList(String name, int page, int rows, String sort, String order);

	PatrolDeviceType selectById(String id);

	List<PatrolDeviceType> queryCBBList();

}

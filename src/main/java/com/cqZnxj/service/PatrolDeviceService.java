package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.PatrolDeviceType;

public interface PatrolDeviceService {

	int queryForInt(String name, String pdtName);

	List<PatrolDeviceType> queryList(String name, String pdtName, int page, int rows, String sort, String order);

}

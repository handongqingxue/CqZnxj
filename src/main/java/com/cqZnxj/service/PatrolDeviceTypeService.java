package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolDeviceTypeService {

	int queryForInt(String mc);

	List<PatrolDeviceType> queryList(String mc, int page, int rows, String sort, String order);

}

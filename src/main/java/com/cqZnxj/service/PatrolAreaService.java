package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolAreaService {

	int add(PatrolArea pa);

	int queryForInt(String name, Integer deptId, String deptName);

	List<PatrolArea> queryList(String name, Integer deptId, String deptName, int page, int rows, String sort,
			String order);

}

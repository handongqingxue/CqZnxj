package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolAreaService {

	int add(PatrolArea pa);

	int deleteByIds(String ids);

	int edit(PatrolArea pa);

	int queryForInt(String name, Integer deptId, String deptName,String createTimeStart,String createTimeEnd);

	List<PatrolArea> queryList(String name, Integer deptId, String deptName,String createTimeStart,String createTimeEnd, int page, int rows, String sort,
			String order);

	PatrolArea selectById(String id);

	List<PatrolArea> queryCBBList(int deptId);

	List<PatrolArea> selectPhListByPlId(Integer plId);

}

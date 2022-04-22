package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolLineService {

	int add(PatrolLine pl);

	int deleteByIds(String ids);

	int edit(PatrolLine pl);

	int queryForInt(String name, String createTimeStart, String createTimeEnd);

	List<PatrolLine> queryList(String name, String createTimeStart, String createTimeEnd, int page, int rows,
			String sort, String order);

	List<PatrolLine> queryCBBList();

	List<PatrolLine> getTotalInfo();

}

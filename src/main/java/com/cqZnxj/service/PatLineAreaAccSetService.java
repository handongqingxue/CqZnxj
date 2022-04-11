package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatLineAreaAccSetService {

	int queryForInt(String plName, String paName);

	List<PatLineAreaAccSet> queryList(String plName, String paName, int page, int rows, String sort, String order);

	int add(PatLineAreaAccSet plaas);

	int deleteByIds(String ids);

	int edit(PatLineAreaAccSet plaas);

}

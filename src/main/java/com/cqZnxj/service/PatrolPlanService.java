package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolPlanService {

	int add(PatrolPlan pp);

	int queryForInt(String name,Integer state);

	List<PatrolPlan> queryList(String name, Integer state, int page, int rows, String sort, String order);

}

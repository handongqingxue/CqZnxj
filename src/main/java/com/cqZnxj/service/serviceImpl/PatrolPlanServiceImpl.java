package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatrolPlanServiceImpl implements PatrolPlanService {

	@Autowired
	private PatrolPlanMapper patrolPlanDao;

	@Override
	public int queryForInt(String name,Integer state) {
		// TODO Auto-generated method stub
		return patrolPlanDao.queryForInt(name,state);
	}

	@Override
	public List<PatrolPlan> queryList(String name, Integer state, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return patrolPlanDao.queryList(name, state, (page-1)*rows, rows, sort, order);
	}
}

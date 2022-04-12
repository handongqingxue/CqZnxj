package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatrolTeamServiceImpl implements PatrolTeamService {

	@Autowired
	private PatrolTeamMapper patrolTeamDao;

	@Override
	public int queryForInt(String name) {
		// TODO Auto-generated method stub
		return patrolTeamDao.queryForInt(name);
	}

	@Override
	public List<PatrolTeam> queryList(String name, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return patrolTeamDao.queryList(name, (page-1)*rows, rows, sort, order);
	}
}

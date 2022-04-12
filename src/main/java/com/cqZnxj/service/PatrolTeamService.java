package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolTeamService {

	int queryForInt(String name);

	List<PatrolTeam> queryList(String name, int page, int rows, String sort, String order);

}

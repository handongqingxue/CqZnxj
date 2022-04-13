package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface PatrolTeamService {

	int add(PatrolTeam pt);

	int edit(PatrolTeam pt);

	int queryForInt(String name);

	List<PatrolTeam> queryList(String name, int page, int rows, String sort, String order);

	PatrolTeam selectById(String id);

	int deleteByIds(String ids);

}

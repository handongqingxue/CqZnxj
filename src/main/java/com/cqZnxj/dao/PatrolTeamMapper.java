package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface PatrolTeamMapper {

	int add(PatrolTeam pt);

	int deleteByIds(List<String> idList);

	int edit(PatrolTeam pt);

	int queryForInt(@Param("name") String name);

	List<PatrolTeam> queryList(@Param("name") String name, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	PatrolTeam selectById(String id);

	List<PatrolTeam> queryCBBList();

	List<PatrolTeam> queryCBBListByDeptId(@Param("deptId") Integer deptId);

	PatrolTeam selectByStaffId(@Param("staffId") Integer staffId);

}

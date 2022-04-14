package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface PatrolPlanMapper {

	int add(PatrolPlan pp);

	int deleteByIdList(List<String> idList);

	int edit(PatrolPlan pp);

	int queryForInt(@Param("name") String name, @Param("state") Integer state);

	List<PatrolPlan> queryList(@Param("name") String name, @Param("state") Integer state, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	PatrolPlan selectById(String id);

}

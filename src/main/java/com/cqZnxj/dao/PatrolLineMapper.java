package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.PatrolLine;

public interface PatrolLineMapper {

	int add(PatrolLine pl);

	int deleteByIds(List<String> idList);

	int edit(PatrolLine pl);

	int queryForInt(@Param("name") String name, @Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd);

	List<PatrolLine> queryList(@Param("name") String name, @Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	List<PatrolLine> queryCBBList();

}

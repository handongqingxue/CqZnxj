package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface PatrolDeviceTypeMapper {
	
	int add(PatrolDeviceType pdt);

	int edit(PatrolDeviceType pdt);

	int queryForInt(@Param("name") String name);

	List<PatrolDeviceType> queryList(@Param("name") String name, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	PatrolDeviceType selectById(String id);

	List<PatrolDeviceType> queryCBBList();

	int deleteByIds(List<String> idList);

}

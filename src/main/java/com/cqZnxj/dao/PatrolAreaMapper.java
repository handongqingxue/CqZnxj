package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface PatrolAreaMapper {

	int add(PatrolArea pa);

	int queryForInt(@Param("name") String name, @Param("deptId") Integer deptId, @Param("deptName") String deptName,@Param("createTimeStart") String createTimeStart,@Param("createTimeEnd") String createTimeEnd);

	List<PatrolArea> queryList(@Param("name") String name, @Param("deptId") Integer deptId, @Param("deptName") String deptName,@Param("createTimeStart") String createTimeStart,@Param("createTimeEnd") String createTimeEnd, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

}

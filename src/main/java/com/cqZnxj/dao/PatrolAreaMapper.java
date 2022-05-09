package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface PatrolAreaMapper {

	int add(PatrolArea pa);

	int deleteByIds(List<String> idList);

	int edit(PatrolArea pa);

	int queryForInt(@Param("name") String name, @Param("deptId") Integer deptId, @Param("secondDeptName") String secondDeptName,@Param("createTimeStart") String createTimeStart,@Param("createTimeEnd") String createTimeEnd);

	List<PatrolArea> queryList(@Param("name") String name, @Param("deptId") Integer deptId, @Param("secondDeptName") String secondDeptName,@Param("createTimeStart") String createTimeStart,@Param("createTimeEnd") String createTimeEnd, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	PatrolArea selectById(String id);

	List<PatrolArea> queryCBBList(@Param("deptId") int deptId);

	String getPdaIdsById(@Param("id") String id);

	List<PatrolArea> selectPhListByPlId(@Param("plId") Integer plId);

	List<String> selectXAxisData(@Param("ptId") Integer ptId);

}

package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface StaffMapper {

	int add(Staff staff);

	int edit(Staff staff);

	List<Staff> queryCBBList(@Param("deptId") String deptId);

	List<Staff> queryCBBListByPtId(@Param("ptId") Integer ptId);

	Staff selectByJobNumber(@Param("jobNumber") String jobNumber);

	int queryForInt(@Param("name") String name, @Param("deptId") Integer deptId, @Param("secondDeptName") String secondDeptName);

	List<Staff> queryList(@Param("name") String name, @Param("deptId") Integer deptId, @Param("secondDeptName") String secondDeptName, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	Staff selectByUuid(String uuid);

}

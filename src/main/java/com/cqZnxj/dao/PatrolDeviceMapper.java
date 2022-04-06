package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface PatrolDeviceMapper {

	int add(PatrolDevice pd);

	int queryForInt(@Param("name") String name, @Param("deptName") String deptName);

	List<PatrolDevice> queryList(@Param("name") String name, @Param("deptName") String deptName, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	int deleteByIds(List<String> idList);

	PatrolDevice selectById(String id);

	int edit(PatrolDevice pd);

	List<PatrolDevice> queryCBBList(@Param("deptId") String deptId);

}

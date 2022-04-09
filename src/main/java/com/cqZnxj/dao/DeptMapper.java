package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface DeptMapper {

	List<TreeNode> queryTreeList(@Param("parentId") int parentId);

	int getCountByParentId(@Param("parentId") int parentId);
	
	int queryForInt(@Param("deptName") String deptName);

	List<Dept> queryList(@Param("deptName") String deptName, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	Dept selectByDeptId(@Param("deptId") String deptId);

	List<Dept> queryCBBList(@Param("parentId") Integer parentId);

}

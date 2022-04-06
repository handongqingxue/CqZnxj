package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface DeptService {

	List<TreeNode> queryTreeList(int parentId);

	int queryForInt(String deptName);

	List<Dept> queryList(String deptName, int page, int rows, String sort, String order);

	Dept selectByDeptId(String deptId);

	List<Dept> queryCBBList();
}

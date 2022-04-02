package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface DeptMapper {

	List<TreeNode> queryTreeList(@Param("parentId") int parentId);

	int getCountByParentId(@Param("parentId") int id);

}

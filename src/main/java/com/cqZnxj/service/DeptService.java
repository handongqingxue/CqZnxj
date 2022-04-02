package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface DeptService {

	List<TreeNode> queryTreeList(int parentId);

}

package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptDao;

	@Override
	public List<TreeNode> queryTreeList(int parentId) {
		// TODO Auto-generated method stub
		List<TreeNode> tnList = deptDao.queryTreeList(parentId);
		for (int i = 0; i < tnList.size(); i++) {
			TreeNode tn = tnList.get(i);
			int id = tn.getId();
			if(deptDao.getCountByParentId(id)>0) {
				List<TreeNode> childTnList = deptDao.queryTreeList(id);
				tn.setChildren(childTnList);
			}
		}
		return tnList;
	}
}

package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface StaffService {

	List<Staff> queryCBBList(String deptId);

	List<Staff> queryCBBListByPtId(Integer ptId);

	Staff selectByJobNumber(String jobNumber);

}

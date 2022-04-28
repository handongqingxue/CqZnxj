package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface DevParPatRecService {

	int add(DevParPatRec dppr);

	int editByPdpIdPtId(DevParPatRec dppr);

	DevParPatRec selectByPdpIdPtId(Integer pdpId, Integer ptId);

	boolean checkIfExist(Integer pdpId, Integer ptId);

	int updateFileUrlByPdpIdPtId(DevParPatRec dppr);

	int queryForInt(String plName, String paName, String pdName, String pdaNo, String pdpName, String pdpUnit, Integer pdLevel,
			String startTime, String endTime);

	List<DevParPatRec> queryList(String plName, String paName, String pdName, String pdaNo, String pdpName, String pdpUnit, Integer pdLevel,
			String startTime, String endTime, int page, int rows, String sort, String order);

	DevParPatRec selectById(String id);

}

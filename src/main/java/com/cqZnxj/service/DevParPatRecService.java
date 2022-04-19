package com.cqZnxj.service;

import com.cqZnxj.entity.*;

public interface DevParPatRecService {

	int add(DevParPatRec dppr);

	int editByPdpIdPtId(DevParPatRec dppr);

	DevParPatRec selectByPdpIdPtId(Integer pdpId, Integer ptId);

	boolean checkIfExist(Integer pdpId, Integer ptId);

}

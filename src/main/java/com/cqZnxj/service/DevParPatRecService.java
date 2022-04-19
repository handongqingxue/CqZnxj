package com.cqZnxj.service;

import com.cqZnxj.entity.*;

public interface DevParPatRecService {

	int save(DevParPatRec dppr);

	DevParPatRec selectByPdpIdPtId(Integer pdpId, Integer ptId);

}

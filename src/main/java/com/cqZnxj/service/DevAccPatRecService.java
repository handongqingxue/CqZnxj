package com.cqZnxj.service;

import com.cqZnxj.entity.*;

public interface DevAccPatRecService {

	int add(DevAccPatRec dapr);

	boolean checkIfExist(Integer pdaId, Integer ptId);

	int getIdByPdaIdPtId(Integer pdaId, Integer ptId);

}

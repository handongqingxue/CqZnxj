package com.cqZnxj.service;

import com.cqZnxj.entity.*;

public interface AreaPatRecService {

	int add(AreaPatRec apr);

	boolean checkIfExist(Integer paId, Integer ptId);

	int getIdByPaIdPtId(Integer paId, Integer ptId);

}

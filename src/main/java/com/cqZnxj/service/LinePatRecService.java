package com.cqZnxj.service;

import com.cqZnxj.entity.*;

public interface LinePatRecService {

	int add(LinePatRec lpr);

	boolean checkIfExist(Integer plId, Integer ptId);

	int getIdByPlIdPtId(Integer plId, Integer ptId);

}

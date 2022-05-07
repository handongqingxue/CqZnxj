package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface AreaPatRecService {

	int add(AreaPatRec apr);

	boolean checkIfExist(Integer paId, Integer ptId);

	int getIdByPaIdPtId(Integer paId, Integer ptId);

	float getReachPercent(Integer ptId);

	List<AreaPatRec> selectBarChartData(Integer ptId);

}

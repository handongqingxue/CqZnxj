package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface LinePatRecService {

	int add(LinePatRec lpr);

	boolean checkIfExist(Integer plId, Integer ptId);

	int getIdByPlIdPtId(Integer plId, Integer ptId);

	List<LinePatRec> getTodayList();

	float getReachPercent(Integer recently, Integer ptId, Integer staffId, String startDate, String endDate);

	List<LinePatRec> selectBarChartData(Integer recently, Integer ptId, Integer staffId, String startDate, String endDate);

	int getReachDayCount(Integer recently, Integer ptId, Integer staffId, String startDate, String endDate);

}

package com.cqZnxj.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class LinePatRecServiceImpl implements LinePatRecService {

	@Autowired
	private LinePatRecMapper linePatRecDao;

	@Override
	public int add(LinePatRec lpr) {
		// TODO Auto-generated method stub
		return linePatRecDao.add(lpr);
	}

	@Override
	public boolean checkIfExist(Integer plId, Integer ptId) {
		// TODO Auto-generated method stub
		return linePatRecDao.getCount(plId,ptId)==0?false:true;
	}

	@Override
	public int getIdByPlIdPtId(Integer plId, Integer ptId) {
		// TODO Auto-generated method stub
		return linePatRecDao.getIdByPlIdPtId(plId, ptId);
	}

	@Override
	public List<LinePatRec> getTodayList() {
		// TODO Auto-generated method stub
		return linePatRecDao.getTodayList();
	}

	@Override
	public float getReachPercent(Integer ptId) {
		// TODO Auto-generated method stub
		int finishCount=0;
		int sumCount=0;
		List<Map<String,Object>> countMapList=linePatRecDao.getIfFinishCount(ptId);
		for (int i = 0; i < countMapList.size(); i++) {
			Map<String, Object> countMap = countMapList.get(i);
			Boolean finish = Boolean.valueOf(countMap.get("finish").toString());
			Integer count = Integer.valueOf(countMap.get("count").toString());
			if(finish) {
				finishCount+=count;
			}
			sumCount+=count;
		}
		return finishCount/sumCount*100;
	}
}

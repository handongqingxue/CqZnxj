package com.cqZnxj.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class AreaPatRecServiceImpl implements AreaPatRecService {

	@Autowired
	private AreaPatRecMapper areaPatRecDao;

	@Override
	public int add(AreaPatRec apr) {
		// TODO Auto-generated method stub
		return areaPatRecDao.add(apr);
	}

	@Override
	public boolean checkIfExist(Integer paId, Integer ptId) {
		// TODO Auto-generated method stub
		return areaPatRecDao.getCount(paId,ptId)==0?false:true;
	}

	@Override
	public int getIdByPaIdPtId(Integer paId, Integer ptId) {
		// TODO Auto-generated method stub
		return areaPatRecDao.getIdByPaIdPtId(paId, ptId);
	}

	@Override
	public float getReachPercent(Integer ptId) {
		// TODO Auto-generated method stub
		int finishCount=0;
		int sumCount=0;
		List<Map<String,Object>> countMapList=areaPatRecDao.getIfFinishCount(ptId);
		for (int i = 0; i < countMapList.size(); i++) {
			Map<String, Object> countMap = countMapList.get(i);
			Boolean finish = Boolean.valueOf(countMap.get("finish").toString());
			Integer count = Integer.valueOf(countMap.get("count").toString());
			if(finish) {
				finishCount+=count;
			}
			sumCount+=count;
		}
		return sumCount==0?0:finishCount/sumCount*100;
	}

	@Override
	public List<AreaPatRec> selectBarChartData(Integer ptId) {
		// TODO Auto-generated method stub
		return areaPatRecDao.selectBarChartData(ptId);
	}
}

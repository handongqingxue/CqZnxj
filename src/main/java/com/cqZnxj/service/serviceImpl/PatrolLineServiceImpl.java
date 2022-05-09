package com.cqZnxj.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatrolLineServiceImpl implements PatrolLineService {

	@Autowired
	private PatrolLineMapper patrolLineDao;
	@Autowired
	private DevAccPatRecMapper devAccPatRecDao;
	@Autowired
	private AreaPatRecMapper areaPatRecDao;

	@Override
	public int add(PatrolLine pl) {
		// TODO Auto-generated method stub
		return patrolLineDao.add(pl);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=patrolLineDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(PatrolLine pl) {
		// TODO Auto-generated method stub
		return patrolLineDao.edit(pl);
	}

	@Override
	public int queryForInt(String name, String createTimeStart, String createTimeEnd) {
		// TODO Auto-generated method stub
		return patrolLineDao.queryForInt(name, createTimeStart, createTimeEnd);
	}

	@Override
	public List<PatrolLine> queryList(String name, String createTimeStart, String createTimeEnd, int page, int rows,
			String sort, String order) {
		// TODO Auto-generated method stub
		return patrolLineDao.queryList(name, createTimeStart, createTimeEnd, (page-1)*rows, rows, sort, order);
	}

	@Override
	public List<PatrolLine> queryCBBList() {
		// TODO Auto-generated method stub
		return patrolLineDao.queryCBBList();
	}

	@Override
	public List<PatrolLine> getTotalInfo() {
		// TODO Auto-generated method stub
		List<PatrolLine> plList = patrolLineDao.getTotalInfo();
		List<DevAccPatRec> daprList = devAccPatRecDao.getTodayList();
		List<AreaPatRec> aprList = areaPatRecDao.getTodayList();
		for (int i = 0; i < plList.size(); i++) {
			PatrolLine pl = plList.get(i);
			int finishParCount = 0;
			int patParCount = 0;
			for (int j = 0; j < daprList.size(); j++) {
				DevAccPatRec dapr = daprList.get(j);
				if(pl.getId()==dapr.getPlId()) {
					finishParCount += dapr.getFinishParCount();
					patParCount += dapr.getPatParCount();
				}
			}
			pl.setFinishParCount(finishParCount);
			pl.setPatParCount(patParCount);

			int finishAccCount = 0;
			int patAccCount = 0;
			int finishAccPercent = 0;
			for (int j = 0; j < aprList.size(); j++) {
				AreaPatRec apr = aprList.get(j);
				if(pl.getId()==apr.getPlId()) {
					finishAccCount += apr.getFinishAccCount();
					patAccCount += apr.getPatAccCount();
				}
			}
			if(patAccCount>0)
				finishAccPercent=finishAccCount/patAccCount*100;
			
			pl.setFinishAccCount(finishAccCount);
			pl.setPatAccCount(patAccCount);
			pl.setFinishAccPercent(finishAccPercent);
		}
		return plList;
	}

	@Override
	public PatrolLine selectById(Integer id) {
		// TODO Auto-generated method stub
		return patrolLineDao.selectById(id);
	}

	@Override
	public List<String> selectXAxisData(Integer ptId) {
		// TODO Auto-generated method stub
		return patrolLineDao.selectXAxisData(ptId);
	}
}

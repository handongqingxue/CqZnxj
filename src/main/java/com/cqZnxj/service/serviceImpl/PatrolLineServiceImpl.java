package com.cqZnxj.service.serviceImpl;

import java.util.ArrayList;
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
	@Autowired
	private PatLineAreaAccSetMapper patLineAreaAccSetDao;
	@Autowired
	private PatrolDeviceParamMapper patrolDeviceParamDao;

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
	public List<PatrolLine> getPhList(int ppType) {
		// TODO Auto-generated method stub
		List<PatrolLine> plList = patrolLineDao.getPhList(ppType);
		List<DevAccPatRec> daprList = devAccPatRecDao.getTodayList();
		List<AreaPatRec> aprList = areaPatRecDao.getTodayList();
		List<Integer> plIdList=new ArrayList<Integer>();
		for (PatrolLine pl : plList) {
			Integer plId=pl.getId();
			plIdList.add(plId);
		}
		List<PatrolDeviceParam> pdpList=patrolDeviceParamDao.getListByPlIdList(plIdList);
		List<PatLineAreaAccSet> plaasList=patLineAreaAccSetDao.getListByPlIdList(plIdList);
		for (int i = 0; i < plList.size(); i++) {
			PatrolLine pl = plList.get(i);
			int patParCount = 0;
			for (int j = 0; j < pdpList.size(); j++) {//根据路线、区域、设备台账分配情况，统计本次巡检计划中的巡项数量，该数量是每条巡检路线下的数量
				PatrolDeviceParam pdp = pdpList.get(j);
				if(pl.getId()==pdp.getPlId()) {
					patParCount++;
				}
			}
			pl.setPatParCount(patParCount);
			
			int patAccCount = 0;
			for (int j = 0; j < plaasList.size(); j++) {//根据路线、区域、设备台账分配情况，统计本次巡检计划中的巡点（设备台账）数量，该数量是每条巡检路线下的数量
				PatLineAreaAccSet plaas = plaasList.get(j);
				if(pl.getId()==plaas.getPlId()) {
					String pdaIds = plaas.getPdaIds();
					String[] pdaIdArr = pdaIds.split(",");
					patAccCount += pdaIdArr.length;
				}
			}
			pl.setPatAccCount(patAccCount);
		}
		for (int i = 0; i < plList.size(); i++) {
			PatrolLine pl = plList.get(i);
			int finishParCount = 0;
			for (int j = 0; j < daprList.size(); j++) {
				DevAccPatRec dapr = daprList.get(j);
				if(pl.getId()==dapr.getPlId()) {
					finishParCount += dapr.getFinishParCount();
				}
			}
			pl.setFinishParCount(finishParCount);

			int finishAccCount = 0;
			int finishAccPercent = 0;
			for (int j = 0; j < aprList.size(); j++) {
				AreaPatRec apr = aprList.get(j);
				if(pl.getId()==apr.getPlId()) {
					finishAccCount += apr.getFinishAccCount();
				}
			}
			Integer patAccCount = pl.getPatAccCount();
			if(patAccCount>0)
				finishAccPercent=finishAccCount/patAccCount*100;
			
			pl.setFinishAccCount(finishAccCount);
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
	public List<String> selectXAxisData(Integer recently, Integer ptId, Integer staffId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return patrolLineDao.selectXAxisData(recently, ptId, staffId, startDate, endDate);
	}
}

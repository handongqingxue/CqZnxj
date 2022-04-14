package com.cqZnxj.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatrolPlanServiceImpl implements PatrolPlanService {

	@Autowired
	private PatrolPlanMapper patrolPlanDao;
	@Autowired
	private PatrolTeamMapper patrolTeamDao;
	@Autowired
	private PatrolLineMapper patrolLineDao;

	@Override
	public int add(PatrolPlan pp) {
		// TODO Auto-generated method stub
		return patrolPlanDao.add(pp);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		String[] idArr = ids.split(",");
		List<String> idList = Arrays.asList(idArr);
		return patrolPlanDao.deleteByIdList(idList);
	}

	@Override
	public int edit(PatrolPlan pp) {
		// TODO Auto-generated method stub
		return patrolPlanDao.edit(pp);
	}

	@Override
	public int queryForInt(String name,Integer state) {
		// TODO Auto-generated method stub
		return patrolPlanDao.queryForInt(name,state);
	}

	@Override
	public List<PatrolPlan> queryList(String name, Integer state, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		List<PatrolPlan> ppList = patrolPlanDao.queryList(name, state, (page-1)*rows, rows, sort, order);
		List<PatrolTeam> ptList = patrolTeamDao.queryCBBList();
		List<PatrolLine> plList = patrolLineDao.queryCBBList();
		for (int i = 0; i < ppList.size(); i++) {
			PatrolPlan pp = ppList.get(i);
			String ptIds = pp.getPtIds();
			String[] ptIdArr = ptIds.split(",");
			String ptNames = "";
			for (String ptIdStr : ptIdArr) {
				int ptId = Integer.valueOf(ptIdStr);
				for (int j = 0; j < ptList.size(); j++) {
					PatrolTeam pt = ptList.get(j);
					if(ptId==pt.getId()) {
						ptNames+=","+pt.getName();
						break;
					}
				}
			}
			pp.setPtNames(ptNames.substring(1));

			String plIds = pp.getPlIds();
			String[] plIdArr = plIds.split(",");
			String plNames = "";
			for (String plIdStr : plIdArr) {
				int plId = Integer.valueOf(plIdStr);
				for (int j = 0; j < plList.size(); j++) {
					PatrolLine pl = plList.get(j);
					if(plId==pl.getId()) {
						plNames+=","+pl.getName();
						break;
					}
				}
			}
			pp.setPlNames(plNames.substring(1));
		}
		return ppList;
	}

	@Override
	public PatrolPlan selectById(String id) {
		// TODO Auto-generated method stub
		PatrolPlan pp = patrolPlanDao.selectById(id);
		List<PatrolTeam> ptList = patrolTeamDao.queryCBBList();
		List<PatrolLine> plList = patrolLineDao.queryCBBList();
		String ptIds = pp.getPtIds();
		String[] ptIdArr = ptIds.split(",");
		String ptNames = "";
		for (String ptIdStr : ptIdArr) {
			int ptId = Integer.valueOf(ptIdStr);
			for (int j = 0; j < ptList.size(); j++) {
				PatrolTeam pt = ptList.get(j);
				if(ptId==pt.getId()) {
					ptNames+=","+pt.getName();
					break;
				}
			}
		}
		pp.setPtNames(ptNames.substring(1));
		
		String plIds = pp.getPlIds();
		String[] plIdArr = plIds.split(",");
		String plNames = "";
		for (String plIdStr : plIdArr) {
			int plId = Integer.valueOf(plIdStr);
			for (int j = 0; j < plList.size(); j++) {
				PatrolLine pl = plList.get(j);
				if(plId==pl.getId()) {
					plNames+=","+pl.getName();
					break;
				}
			}
		}
		pp.setPlNames(plNames.substring(1));
		return pp;
	}
}

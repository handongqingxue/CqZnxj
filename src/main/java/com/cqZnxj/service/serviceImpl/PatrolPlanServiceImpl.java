package com.cqZnxj.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
	@Autowired
	private StaffMapper staffDao;

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
		List<Staff> psList = staffDao.queryCBBList(null);
		List<PatrolLine> plList = patrolLineDao.queryCBBList();
		for (int i = 0; i < ppList.size(); i++) {
			PatrolPlan pp = ppList.get(i);
			String psIds = pp.getPsIds();
			String[] psIdArr = psIds.split(",");
			String psNames = "";
			for (String psIdStr : psIdArr) {
				int psId = Integer.valueOf(psIdStr);
				for (int j = 0; j < psList.size(); j++) {
					Staff ps = psList.get(j);
					if(psId==ps.getId()) {
						psNames+=","+ps.getName();
						break;
					}
				}
			}
			pp.setPsNames(psNames.substring(1));
			
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
		List<Staff> psList = staffDao.queryCBBList(null);
		List<PatrolLine> plList = patrolLineDao.queryCBBList();
		
		String psIds = pp.getPsIds();
		String[] psIdArr = psIds.split(",");
		String psNames = "";
		for (String psIdStr : psIdArr) {
			int psId = Integer.valueOf(psIdStr);
			for (int j = 0; j < psList.size(); j++) {
				Staff ps = psList.get(j);
				if(psId==ps.getId()) {
					psNames+=","+ps.getName();
					break;
				}
			}
		}
		pp.setPsNames(psNames.substring(1));
		
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

	@Override
	public int getSumDayCount(Integer ptId) {
		// TODO Auto-generated method stub
		String countStr=patrolPlanDao.getSumDayCount(ptId);
		return StringUtils.isEmpty(countStr)?0:Integer.valueOf(countStr);
	}

	@Override
	public int getReachDayCount(Integer ptId) {
		// TODO Auto-generated method stub
		return patrolPlanDao.getReachDayCount(ptId);
	}
}

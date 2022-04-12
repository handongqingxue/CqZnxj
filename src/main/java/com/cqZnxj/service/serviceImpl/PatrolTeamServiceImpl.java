package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatrolTeamServiceImpl implements PatrolTeamService {

	@Autowired
	private PatrolTeamMapper patrolTeamDao;
	@Autowired
	private StaffMapper staffDao;

	@Override
	public int add(PatrolTeam pt) {
		// TODO Auto-generated method stub
		return patrolTeamDao.add(pt);
	}

	@Override
	public int queryForInt(String name) {
		// TODO Auto-generated method stub
		return patrolTeamDao.queryForInt(name);
	}

	@Override
	public List<PatrolTeam> queryList(String name, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		List<PatrolTeam> ptList = patrolTeamDao.queryList(name, (page-1)*rows, rows, sort, order);
		List<Staff> staffList = staffDao.queryCBBList(null);
		for (int i = 0; i < ptList.size(); i++) {
			PatrolTeam pt = ptList.get(i);
			String staffIds = pt.getStaffIds();
			String[] staffIdArr = staffIds.split(",");
			String staffNames = "";
			for (String staffIdStr : staffIdArr) {
				int staffId = Integer.valueOf(staffIdStr);
				for (int j = 0; j < staffList.size(); j++) {
					Staff staff = staffList.get(j);
					if(staffId==staff.getId()) {
						staffNames+=","+staff.getName();
						break;
					}
				}
			}
			pt.setStaffNames(staffNames.substring(1));
		}
		return ptList;
	}
}

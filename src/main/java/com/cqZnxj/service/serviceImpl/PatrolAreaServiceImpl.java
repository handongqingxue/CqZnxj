package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatrolAreaServiceImpl implements PatrolAreaService {

	@Autowired
	private PatrolAreaMapper patrolAreaDao;
	@Autowired
	private PatrolDeviceAccountMapper patrolDeviceAccountDao;

	@Override
	public int queryForInt(String name, Integer deptId, String deptName) {
		// TODO Auto-generated method stub
		return patrolAreaDao.queryForInt(name, deptId, deptName);
	}

	@Override
	public List<PatrolArea> queryList(String name, Integer deptId, String deptName, int page, int rows, String sort,
			String order) {
		// TODO Auto-generated method stub
		List<PatrolArea> paList = patrolAreaDao.queryList(name, deptId, deptName, (page-1)*rows, rows, sort, order);
		List<PatrolDeviceAccount> pdaList = patrolDeviceAccountDao.queryCBBList(null);
		for (int i = 0; i < paList.size(); i++) {
			PatrolArea pa = paList.get(i);
			String pdaIds = pa.getPdaIds();
			String[] pdaIdArr = pdaIds.split(",");
			String pdaNos = "";
			for (String pdaIdStr : pdaIdArr) {
				int pdaId = Integer.valueOf(pdaIdStr);
				for (int j = 0; j < pdaList.size(); j++) {
					PatrolDeviceAccount pda = pdaList.get(j);
					if(pdaId==pda.getId()) {
						pdaNos+=","+pda.getNo();
						break;
					}
				}
			}
			pa.setPdaNos(pdaNos.substring(1));
		}
		return paList;
	}

	@Override
	public int add(PatrolArea pa) {
		// TODO Auto-generated method stub
		return patrolAreaDao.add(pa);
	}
}

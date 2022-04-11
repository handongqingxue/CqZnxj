package com.cqZnxj.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatLineAreaAccSetServiceImpl implements PatLineAreaAccSetService {

	@Autowired
	private PatLineAreaAccSetMapper patLineAreaAccSetDao;
	@Autowired
	private PatrolDeviceAccountMapper patrolDeviceAccountDao;

	@Override
	public int queryForInt(String plName, String paName) {
		// TODO Auto-generated method stub
		return patLineAreaAccSetDao.queryForInt(plName, paName);
	}

	@Override
	public List<PatLineAreaAccSet> queryList(String plName, String paName, int page, int rows, String sort,
			String order) {
		// TODO Auto-generated method stub
		List<PatLineAreaAccSet> plaasList = patLineAreaAccSetDao.queryList(plName, paName, (page-1)*rows, rows, sort, order);
		List<PatrolDeviceAccount> pdaList = patrolDeviceAccountDao.queryCBBList(null,null);
		for (int i = 0; i < plaasList.size(); i++) {
			PatLineAreaAccSet plaas = plaasList.get(i);
			String pdaIds = plaas.getPdaIds();
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
			plaas.setPdaNos(pdaNos.substring(1));
		}
		return plaasList;
	}

	@Override
	public int add(PatLineAreaAccSet plaas) {
		// TODO Auto-generated method stub
		return patLineAreaAccSetDao.add(plaas);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		String[] idArr = ids.split(",");
		List<String> idList = Arrays.asList(idArr);
		return patLineAreaAccSetDao.deleteByIds(idList);
	}

	@Override
	public int edit(PatLineAreaAccSet plaas) {
		// TODO Auto-generated method stub
		return patLineAreaAccSetDao.edit(plaas);
	}
}

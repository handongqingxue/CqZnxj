package com.cqZnxj.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class DevAccPatRecServiceImpl implements DevAccPatRecService {

	@Autowired
	private DevAccPatRecMapper devAccPatRecDao;
	@Autowired
	private AreaPatRecMapper areaPatRecDao;
	@Autowired
	private LinePatRecMapper linePatRecDao;

	@Override
	public int add(DevAccPatRec dapr) {
		// TODO Auto-generated method stub
		return devAccPatRecDao.add(dapr);
	}

	@Override
	public boolean checkIfExist(Integer pdaId, Integer ptId) {
		// TODO Auto-generated method stub
		return devAccPatRecDao.getCount(pdaId,ptId)==0?false:true;
	}

	@Override
	public int getIdByPdaIdPtId(Integer pdaId, Integer ptId) {
		// TODO Auto-generated method stub
		return devAccPatRecDao.getIdByPdaIdPtId(pdaId, ptId);
	}

	@Override
	public int updateFinishCountById(int daprId, int aprId, int lprId) {
		// TODO Auto-generated method stub
		int count=devAccPatRecDao.updateFinishParCountById(daprId);
		DevAccPatRec dapr=devAccPatRecDao.selectById(daprId);
		int finishParCount = dapr.getFinishParCount();
		int patParCount = dapr.getPatParCount();
		if(finishParCount==patParCount) {
			count=devAccPatRecDao.updateFinishById(daprId);
			if(count>0) {
				areaPatRecDao.updateFinishAccCountById(aprId);
				AreaPatRec apr=areaPatRecDao.selectById(aprId);
				int finishAccCount = apr.getFinishAccCount();
				int patAccCount = apr.getPatAccCount();
				if(finishAccCount==patAccCount) {
					count=areaPatRecDao.updateFinishById(aprId);
					if(count>0) {
						count=linePatRecDao.updateFinishAreaCountById(lprId);
						LinePatRec lpr=linePatRecDao.selectById(lprId);
						int finishAreaCount = lpr.getFinishAreaCount();
						int patAreaCount = lpr.getPatAreaCount();
						if(finishAreaCount==patAreaCount) {
							count=linePatRecDao.updateFinishById(lprId);
						}
					}
				}
			}
		}
		return count;
	}
}

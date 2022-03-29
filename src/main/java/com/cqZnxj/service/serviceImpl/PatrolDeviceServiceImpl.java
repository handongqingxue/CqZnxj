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
public class PatrolDeviceServiceImpl implements PatrolDeviceService {

	@Autowired
	private PatrolDeviceMapper patrolDeviceDao;

	public int add(PatrolDevice pd) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.add(pd);
	}

	public int queryForInt(String name, String pdtName) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.queryForInt(name, pdtName);
	}

	public List<PatrolDeviceType> queryList(String name, String pdtName, int page, int rows, String sort,
			String order) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.queryList(name, pdtName, (page-1)*rows, rows, sort, order);
	}

	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=patrolDeviceDao.deleteByIds(idList);
		return count;
	}

	public PatrolDevice selectById(String id) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.selectById(id);
	}

	public int edit(PatrolDevice pd) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.edit(pd);
	}

	public List<PatrolDeviceType> checkIfExistByPdtIds(String pdtIds, String typeNames) {
		// TODO Auto-generated method stub
		List<PatrolDeviceType> pdtList=new ArrayList<PatrolDeviceType>();
		String[] pdtIdArr = pdtIds.split(",");
		String[] pdtNameArr = typeNames.split(",");
		for (int i = 0; i < pdtIdArr.length; i++) {
			String pdtId = pdtIdArr[i];
			if(patrolDeviceDao.getCountByPdtId(pdtId)>0) {
				PatrolDeviceType pdt=new PatrolDeviceType();
				pdt.setId(Integer.valueOf(pdtId));
				pdt.setName(pdtNameArr[i]);
				pdtList.add(pdt);
			}
		}
		return pdtList;
	}

	public List<PatrolDevice> queryCBBList(String pdtId) {
		// TODO Auto-generated method stub
		return patrolDeviceDao.queryCBBList(pdtId);
	}
}

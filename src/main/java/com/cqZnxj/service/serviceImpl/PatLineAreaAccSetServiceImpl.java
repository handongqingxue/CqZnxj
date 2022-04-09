package com.cqZnxj.service.serviceImpl;

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

	@Override
	public int queryForInt(String plName, String paName) {
		// TODO Auto-generated method stub
		return patLineAreaAccSetDao.queryForInt(plName, paName);
	}

	@Override
	public List<PatLineAreaAccSet> queryList(String plName, String paName, int page, int rows, String sort,
			String order) {
		// TODO Auto-generated method stub
		return patLineAreaAccSetDao.queryList(plName, paName, (page-1)*rows, rows, sort, order);
	}
}

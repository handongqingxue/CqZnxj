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
public class PatrolAreaServiceImpl implements PatrolAreaService {

	@Autowired
	private PatrolAreaMapper patrolAreaDao;
	@Autowired
	private PatrolDeviceAccountMapper patrolDeviceAccountDao;
	@Autowired
	private PatrolDeviceParamMapper patrolDeviceParamDao;
	@Autowired
	private AreaPatRecMapper areaPatRecDao;
	@Autowired
	private DevAccPatRecMapper devAccPatRecDao;

	@Override
	public int add(PatrolArea pa) {
		// TODO Auto-generated method stub
		return patrolAreaDao.add(pa);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=patrolAreaDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(PatrolArea pa) {
		// TODO Auto-generated method stub
		return patrolAreaDao.edit(pa);
	}

	@Override
	public int queryForInt(String name, Integer deptId, String secondDeptName, String createTimeStart, String createTimeEnd) {
		// TODO Auto-generated method stub
		return patrolAreaDao.queryForInt(name, deptId, secondDeptName, createTimeStart, createTimeEnd);
	}

	@Override
	public List<PatrolArea> queryList(String name, Integer deptId, String secondDeptName,String createTimeStart,String createTimeEnd, int page, int rows, String sort,
			String order) {
		// TODO Auto-generated method stub
		List<PatrolArea> paList = patrolAreaDao.queryList(name, deptId, secondDeptName, createTimeStart, createTimeEnd, (page-1)*rows, rows, sort, order);
		List<PatrolDeviceAccount> pdaList = patrolDeviceAccountDao.queryCBBList(null,null);
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
	public PatrolArea selectById(String id) {
		// TODO Auto-generated method stub
		PatrolArea pa = patrolAreaDao.selectById(id);
		List<PatrolDeviceAccount> pdaList = patrolDeviceAccountDao.queryCBBList(pa.getDeptId().toString(),null);
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
		return pa;
	}

	@Override
	public List<PatrolArea> queryCBBList(int deptId) {
		// TODO Auto-generated method stub
		return patrolAreaDao.queryCBBList(deptId);
	}

	@Override
	public List<PatrolArea> selectPhListByPlId(Integer plId) {
		// TODO Auto-generated method stub
		List<PatrolArea> paList = patrolAreaDao.selectPhListByPlId(plId);
		List<AreaPatRec> aprList = areaPatRecDao.getTodayList();
		List<PatrolDeviceAccount> pdaList = patrolDeviceAccountDao.selectPhListByPlId(plId);
		List<Integer> plIdList=new ArrayList<Integer>();
		plIdList.add(plId);
		List<PatrolDeviceParam> pdpList=patrolDeviceParamDao.getListByPlIdList(plIdList);
		List<DevAccPatRec> daprList = devAccPatRecDao.getTodayList();
		for (int i = 0; i < paList.size(); i++) {
			PatrolArea pa = paList.get(i);
			for (int j = 0; j < aprList.size(); j++) {
				AreaPatRec apr = aprList.get(j);
				if(pa.getId()==apr.getPaId()) {
					Boolean finish = apr.getFinish();
					pa.setFinish(finish);
					break;
				}
			}
			
			for (int j = 0; j < pdaList.size(); j++) {
				PatrolDeviceAccount pda = pdaList.get(j);
				if(pa.getId()==pda.getPaId()) {
					List<PatrolDeviceAccount> paPdaList = pa.getPdaList();
					if(paPdaList==null) {
						paPdaList=new ArrayList<>();
						pa.setPdaList(paPdaList);
					}
					paPdaList.add(pda);
				}
			}
		}
		
		for (int i = 0; i < paList.size(); i++) {
			PatrolArea pa = paList.get(i);
			List<PatrolDeviceAccount> paPdaList = pa.getPdaList();
			for (int j = 0; j < paPdaList.size(); j++) {
				PatrolDeviceAccount paPda = paPdaList.get(j);
				for (int z = 0; z < pdpList.size(); z++) {
					PatrolDeviceParam pdp = pdpList.get(z);
					if(paPda.getId()==pdp.getPdaId()) {
						int patParCount = paPda.getPatParCount();
						patParCount++;
						paPda.setPatParCount(patParCount);//统计每个设备台账的设备参数数量
					}
				}
				
				for (int z = 0; z < daprList.size(); z++) {
					DevAccPatRec dapr = daprList.get(z);
					if(paPda.getId()==dapr.getPdaId()) {
						Boolean finish = dapr.getFinish();
						int finishParCount = dapr.getFinishParCount();
						String startTimeHi = dapr.getStartTimeHi();
						String endTimeHi = dapr.getEndTimeHi();
						paPda.setFinish(finish);
						paPda.setFinishParCount(finishParCount);
						paPda.setStartTimeHi(startTimeHi);
						paPda.setEndTimeHi(endTimeHi);
					}
				}
			}
		}
		return paList;
	}

	@Override
	public List<String> selectXAxisData(Integer recently, Integer ptId, Integer staffId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return patrolAreaDao.selectXAxisData(recently, ptId, staffId, startDate, endDate);
	}
}

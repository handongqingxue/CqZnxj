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
	public int queryForInt(String name, Integer deptId, String deptName, String createTimeStart, String createTimeEnd) {
		// TODO Auto-generated method stub
		return patrolAreaDao.queryForInt(name, deptId, deptName, createTimeStart, createTimeEnd);
	}

	@Override
	public List<PatrolArea> queryList(String name, Integer deptId, String deptName,String createTimeStart,String createTimeEnd, int page, int rows, String sort,
			String order) {
		// TODO Auto-generated method stub
		List<PatrolArea> paList = patrolAreaDao.queryList(name, deptId, deptName, createTimeStart, createTimeEnd, (page-1)*rows, rows, sort, order);
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
			
			for (int j = 0; j < daprList.size(); j++) {
				DevAccPatRec dapr = daprList.get(j);
				if(pa.getId()==dapr.getPaId()) {
					List<PatrolDeviceAccount> pdaList = pa.getPdaList();
					if(pdaList==null) {
						pdaList=new ArrayList<>();
						pa.setPdaList(pdaList);
					}
					PatrolDeviceAccount pda=new PatrolDeviceAccount();
					int finishParCount = dapr.getFinishParCount();
					int patParCount = dapr.getPatParCount();
					pda.setFinishParCount(finishParCount);
					pda.setPatParCount(patParCount);
					pdaList.add(pda);
					//select dapr.*,pda.`no` pdaNo,pd.`name` pdName from dev_acc_pat_rec dapr,patrol_device_account pda,patrol_device pd where dapr.pdaId=pda.id and pda.pdId=pd.id and date_format(dapr.createTime,'%Y-%m-%d')=date_format(now(),'%Y-%m-%d')
				}
			}
		}
		return paList;
	}
}

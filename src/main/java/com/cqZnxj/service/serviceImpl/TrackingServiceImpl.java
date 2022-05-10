package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class TrackingServiceImpl implements TrackingService {

	@Autowired
	private TrackingMapper trackingDao;

	@Override
	public List<Tracking> selectCanvasData(Integer areaId, Integer tagId, String timeStart, String timeEnd) {
		// TODO Auto-generated method stub
		return trackingDao.selectCanvasData(areaId, tagId, timeStart, timeEnd);
	}
}

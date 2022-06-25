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

	@Override
	public int add(Tracking tra) {
		// TODO Auto-generated method stub
		float areaLongitudeStart=(float)112.380482;
		float areaLongitudeEnd=(float)130.380482;
		float areaLatitudeStart=(float)26.87649;
		float areaLatitudeEnd=(float)41.87649;
		float mapWidth=500;
		float mapHeight=300;
		float longSev = areaLongitudeEnd-areaLongitudeStart;
		float latSev = areaLatitudeEnd-areaLatitudeStart;
		float longitude = tra.getLongitude();
		float xFloat = (longitude-areaLongitudeStart)*mapWidth/longSev;
		float latitude = tra.getLatitude();
		float yFloat = (latitude-areaLatitudeStart)*mapHeight/latSev;
		tra.setX(xFloat+"");
		tra.setY(yFloat+"");
		return trackingDao.add(tra);
	}
}

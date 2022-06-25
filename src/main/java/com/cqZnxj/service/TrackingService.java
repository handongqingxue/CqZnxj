package com.cqZnxj.service;

import java.util.List;

import com.cqZnxj.entity.*;

public interface TrackingService {

	List<Tracking> selectCanvasData(Integer areaId, Integer tagId, String timeStart, String timeEnd);

	int add(Tracking tra);

}

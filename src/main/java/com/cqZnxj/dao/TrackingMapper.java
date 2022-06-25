package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface TrackingMapper {

	List<Tracking> selectCanvasData(@Param("areaId") Integer areaId, @Param("tagId") Integer tagId, @Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	int add(Tracking tra);

}

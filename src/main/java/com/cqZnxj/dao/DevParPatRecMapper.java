package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface DevParPatRecMapper {

	int add(DevParPatRec dppr);

	int editByPdpIdPtId(DevParPatRec dppr);

	int getCount(@Param("pdpId") Integer pdpId, @Param("ptId") Integer ptId);

	DevParPatRec selectByPdpIdPtId(@Param("pdpId") Integer pdpId, @Param("ptId") Integer ptId);

	int updateFileUrlByPdpIdPtId(DevParPatRec dppr);

	List<DevParPatRec> getTodayList();

	int queryForInt(@Param("plName") String plName, @Param("paName") String paName, @Param("pdName") String pdName, @Param("pdaNo") String pdaNo,
			@Param("pdpName") String pdpName, @Param("pdpUnit") String pdpUnit, @Param("pdLevel") Integer pdLevel, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<DevParPatRec> queryList(@Param("plName") String plName, @Param("paName") String paName, @Param("pdName") String pdName, @Param("pdaNo") String pdaNo,
			@Param("pdpName") String pdpName, @Param("pdpUnit") String pdpUnit, @Param("pdLevel") Integer pdLevel, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("rowNum") int rowNum, @Param("rows") int rows, @Param("sort") String sort, @Param("order") String order);

	DevParPatRec selectById(String id);

}

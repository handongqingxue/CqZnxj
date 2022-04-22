package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface AreaPatRecMapper {

	int add(AreaPatRec apr);

	int getCount(@Param("paId") Integer paId, @Param("ptId") Integer ptId);

	int getIdByPaIdPtId(@Param("paId") Integer paId, @Param("ptId") Integer ptId);

	List<AreaPatRec> getTodayList();

}

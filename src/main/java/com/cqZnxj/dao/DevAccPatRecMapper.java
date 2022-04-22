package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface DevAccPatRecMapper {

	int add(DevAccPatRec dapr);

	int getCount(@Param("pdaId") Integer pdaId, @Param("ptId") Integer ptId);

	int getIdByPdaIdPtId(@Param("pdaId") Integer pdaId, @Param("ptId") Integer ptId);

	List<DevAccPatRec> getTodayList();

}

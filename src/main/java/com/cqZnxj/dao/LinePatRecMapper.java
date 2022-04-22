package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface LinePatRecMapper {

	int add(LinePatRec lpr);

	int getCount(@Param("plId") Integer plId, @Param("ptId") Integer ptId);

	int getIdByPlIdPtId(@Param("plId") Integer plId, @Param("ptId") Integer ptId);

	List<LinePatRec> getTodayList();

}

package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface LinePatRecMapper {

	int add(LinePatRec lpr);

	int getCount(@Param("plId") Integer plId, @Param("ptId") Integer ptId);

	int getIdByPlIdPtId(@Param("plId") Integer plId, @Param("ptId") Integer ptId);

	List<LinePatRec> getTodayList();

	int updateFinishAreaCountById(@Param("id") int id);

	LinePatRec selectById(@Param("id") int id);

	int updateFinishById(@Param("id") int id);

}

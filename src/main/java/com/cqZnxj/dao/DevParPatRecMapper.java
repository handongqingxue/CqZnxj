package com.cqZnxj.dao;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface DevParPatRecMapper {

	int add(DevParPatRec dppr);

	int editByPdpIdPtId(DevParPatRec dppr);

	int getCount(@Param("pdpId") Integer pdpId, @Param("ptId") Integer ptId);

	DevParPatRec selectByPdpIdPtId(@Param("pdpId") Integer pdpId, @Param("ptId") Integer ptId);

	int updateFileUrlByPdpIdPtId(DevParPatRec dppr);

}

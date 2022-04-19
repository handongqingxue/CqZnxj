package com.cqZnxj.dao;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface AreaPatRecMapper {

	int add(AreaPatRec apr);

	int getCount(@Param("paId") Integer paId, @Param("ptId") Integer ptId);

}

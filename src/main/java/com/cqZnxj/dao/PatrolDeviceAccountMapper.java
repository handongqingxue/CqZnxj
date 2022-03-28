package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface PatrolDeviceAccountMapper {

	int queryForInt(@Param("deviceNo") String deviceNo, @Param("pdName") String pdName, @Param("pdtName") String pdtName, 
			@Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, 
			@Param("startTimeStart") String startTimeStart, @Param("startTimeEnd") String startTimeEnd);

	List<PatrolDeviceAccount> queryList(@Param("deviceNo") String deviceNo, @Param("pdName") String pdName, @Param("pdtName") String pdtName, 
			@Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, 
			@Param("startTimeStart") String startTimeStart, @Param("startTimeEnd") String startTimeEnd, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

}

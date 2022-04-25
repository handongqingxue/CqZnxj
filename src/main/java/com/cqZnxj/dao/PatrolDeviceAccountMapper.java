package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface PatrolDeviceAccountMapper {

	int queryForInt(@Param("no") String no, @Param("pdName") String pdName, @Param("deptId") Integer deptId, @Param("deptName") String deptName, 
			@Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, 
			@Param("startTimeStart") String startTimeStart, @Param("startTimeEnd") String startTimeEnd);

	List<PatrolDeviceAccount> queryList(@Param("no") String no, @Param("pdName") String pdName, @Param("deptId") Integer deptId, @Param("deptName") String deptName, 
			@Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, 
			@Param("startTimeStart") String startTimeStart, @Param("startTimeEnd") String startTimeEnd, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	int add(PatrolDeviceAccount pda);

	int getCountByNo(@Param("no") String no);

	PatrolDeviceAccount selectById(@Param("id") String id);

	int edit(PatrolDeviceAccount pda);

	int deleteByIds(List<String> idList);

	List<PatrolDeviceAccount> queryCBBList(@Param("deptId") String deptId,@Param("pdId") String pdId);

	List<PatrolDeviceAccount> queryCBBListByIdList(@Param("pdaIdList") List<String> pdaIdList);

	PatrolDeviceAccount getQrcodeInfoByNo(@Param("no") String no);

	List<PatrolDeviceAccount> selectPhListByPlId(@Param("plId") Integer plId);

}

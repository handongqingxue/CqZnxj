package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.PatrolDeviceParam;

public interface PatrolDeviceParamMapper {

	int queryForInt(@Param("deptId") Integer deptId, @Param("deptName") String deptName, @Param("pdName") String pdName, @Param("pdaNo") String pdaNo, @Param("name") String name, @Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd);

	List<PatrolDeviceParam> queryList(@Param("deptId") Integer deptId, @Param("deptName") String deptName, @Param("pdName") String pdName, @Param("pdaNo") String pdaNo, @Param("name") String name, 
			@Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	int add(PatrolDeviceParam pdp);

	PatrolDeviceParam selectById(String id);

	int edit(PatrolDeviceParam pdp);

	int deleteByIds(List<String> idList);

	int getCountByPdaIdList(@Param("pdaIdList") List<String> pdaIdList);

	int deleteByPdaIdList(@Param("pdaIdList") List<String> pdaIdList);

	List<PatrolDeviceParam> selectPhListByPdaId(@Param("pdaId") Integer pdaId);

	PatrolDeviceParam selectPhInfoById(@Param("id") Integer id);

}

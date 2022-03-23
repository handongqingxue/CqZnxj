package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.PatrolDeviceType;

public interface PatrolDeviceMapper {

	int queryForInt(@Param("name") String name, @Param("pdtName") String pdtName);

	List<PatrolDeviceType> queryList(@Param("name") String name, @Param("pdtName") String pdtName, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

}

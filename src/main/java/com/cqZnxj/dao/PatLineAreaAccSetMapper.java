package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface PatLineAreaAccSetMapper {

	int queryForInt(@Param("plName") String plName, @Param("paName") String paName);

	List<PatLineAreaAccSet> queryList(@Param("plName") String plName, @Param("paName") String paName, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	int add(PatLineAreaAccSet plaas);

	int deleteByIds(List<String> idList);

	int edit(PatLineAreaAccSet plaas);

}

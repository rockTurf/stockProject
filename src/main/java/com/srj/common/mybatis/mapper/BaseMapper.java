package com.srj.common.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.srj.common.mybatis.provider.CommonSqlProvider;

@Repository("baseMapper")
public interface BaseMapper{

	@SelectProvider(type=CommonSqlProvider.class,method="beforeDeleteTreeStructureSql")
	int beforeDeleteTreeStructure(Map<String, Object> params);
	
	@SelectProvider(type=CommonSqlProvider.class,method="findEntityListByDataScope")
	<T> List<Map<String, Object>> findEntityListByDataScope(@Param("record") T record);

	@SelectProvider(type=CommonSqlProvider.class,method="checkUniqueSql")
	int checkUnique(Map<String, Object> params);
	
}

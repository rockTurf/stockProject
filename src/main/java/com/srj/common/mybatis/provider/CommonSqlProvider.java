package com.srj.common.mybatis.provider;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.reflection.MetaObject;

import com.srj.common.constant.Constant;
import com.srj.common.mybatis.EntityHelper;


public class CommonSqlProvider extends BaseProvider{
	
	public String beforeDeleteTreeStructureSql(Map<String, Object> params){
		final String tableNameOne = params.get("t0").toString();
		final String tableNameTwo = params.get("t1").toString();
		final String checkField = params.get("checkField").toString();
		return new SQL(){{
			SELECT("count(0)");
			FROM(tableNameOne+" t0");
			FROM(tableNameTwo+" t1");
			WHERE("t1.parent_ids like concat('%,',#{id},',%') or t1.id=#{id}");
			AND();
			WHERE("t0."+checkField+"=t1.id");
			
		}}.toString();
	}
	
	
	public String checkUniqueSql(final Map<String, Object> params){
		final String tableName = params.get("t").toString();
		return new SQL(){{
			SELECT("count(0)");
			FROM(tableName);
			WHERE("1 = 1");
			for(Map.Entry<String, Object> entry : params.entrySet()){
				if(entry.getKey().equals("id")){
					AND();
					WHERE("id != #{id}");
				}else if(!entry.getKey().equals("t")){
					AND();
					WHERE(""+entry.getKey()+"= #{"+entry.getKey()+"}");
				}
			}
		}}.toString();
	}
	
}

package com.srj.web.datacenter.mapper;

import com.srj.web.datacenter.model.Keyword;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@org.apache.ibatis.annotations.Mapper
public interface KeywordMapper extends Mapper<Keyword> {
	@Select({ "<script>",
			"SELECT id,name,create_name,create_time FROM keyword ",
			"where 1=1 ",
			"<when test='params.name!=null and params.name!=\"\" '>" ,
			"   and name like concat('%',#{params.name} ,'%') " ,
			"</when>",
			"</script>"})
	List<Keyword> findPageInfo(@Param("params")Map<String, Object> params);

	@Select(value = "SELECT id,name FROM keyword where name = #{name}")
	Keyword checkKeyword(@Param("name")String name);

	@Select(value = "SELECT id,name from keyword")
	List<Keyword> getAllKeyword();

	
}

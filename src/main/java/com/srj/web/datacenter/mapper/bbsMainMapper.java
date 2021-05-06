package com.srj.web.datacenter.mapper;

import com.srj.web.datacenter.model.bbsMain;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface bbsMainMapper extends Mapper<bbsMain> {

	@Select({ "<script>",
			"SELECT a.id,a.user_id,a.title,a.content,a.create_time,a.status,b.name as user_name FROM bbs_main a ",
			"LEFT JOIN sys_user b ON b.id = a.user_id",
			"where 1=1 ",
			"<when test='params.title!=null and params.title!=\"\" '>" ,
			"   and a.title like concat('%',#{params.title} ,'%') " ,
			"</when>",
			"</script>"})
	List<bbsMain> findPageInfo(@Param("params") Map<String, Object> params);



}

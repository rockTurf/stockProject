package com.srj.web.datacenter.mapper;

import com.srj.web.datacenter.model.bbsReply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface bbsReplyMapper extends Mapper<bbsReply> {

	@Select({ "<script>",
			"SELECT id,user_id,main_id,floor,content,create_time,status FROM bbs_reply ",
			"where 1=1 ",
			"<when test='params.title!=null and params.title!=\"\" '>" ,
			"   and title like concat('%',#{params.title} ,'%') " ,
			"</when>",
			"</script>"})
	List<bbsReply> findPageInfo(@Param("params") Map<String, Object> params);


}

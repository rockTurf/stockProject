package com.srj.web.datacenter.mapper;

import com.srj.web.datacenter.model.Article;
import com.srj.web.datacenter.model.Bidding;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface BiddingMapper extends Mapper<Bidding> {

	@Select({ "<script>",
			"SELECT id,code,name,create_name,create_time FROM Bidding ",
			"where 1=1 ",
			"<when test='params.name!=null and params.name!=\"\" '>" ,
			"   and name like concat('%',#{params.name} ,'%') " ,
			"</when>",
			"</script>"})
	List<Bidding> findPageInfo(@Param("params")Map<String, Object> params);



}

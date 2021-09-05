package com.srj.web.stock.mapper;


import com.srj.web.stock.model.Fund;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface FundMapper extends tk.mybatis.mapper.common.Mapper<Fund>{
	@Select({"<script>",
			"SELECT * FROM fund",
			"where 1=1 ",
			"</script>"})
	List<Fund> findPageInfo(@Param("params") Map<String, Object> params);

}

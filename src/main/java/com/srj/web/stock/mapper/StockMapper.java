package com.srj.web.stock.mapper;


import com.srj.web.stock.model.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface StockMapper extends tk.mybatis.mapper.common.Mapper<Stock>{
	@Select({"<script>",
			"SELECT id,CODE,NAME,industry,area FROM stock",
			"where 1=1 ",
			"<when test='params.name!=null and params.name!=\"\" '>" ,
			"   and name like concat('%',#{params.name} ,'%') " ,
			"</when>",
			"<when test='params.code!=null and params.code!=\"\" '>" ,
			"   and code = #{params.code} " ,
			"</when>",
			"ORDER BY id desc ",
			"</script>"})
	List<Stock> findPageInfo(@Param("params")Map<String, Object> params);

	String findStockIdByCode(String code);
	@Select(value = "select * FROM stock")
	List<Stock> getAll();


}

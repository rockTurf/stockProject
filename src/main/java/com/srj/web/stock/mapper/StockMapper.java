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
			"SELECT a.id,a.CODE,a.NAME,a.industry,a.area,b.name as board_name FROM stock a",
			"LEFT JOIN stock_board b ON a.board_id = b.id",
			"where 1=1 ",
			"<when test='params.name!=null and params.name!=\"\" '>" ,
			"   and a.name like concat('%',#{params.name} ,'%') " ,
			"</when>",
			"<when test='params.code!=null and params.code!=\"\" '>" ,
			"   and a.code = #{params.code} " ,
			"</when>",
			"ORDER BY a.id desc ",
			"</script>"})
	List<Stock> findPageInfo(@Param("params")Map<String, Object> params);

	String findStockIdByCode(String code);
	@Select(value = "select * FROM stock")
	List<Stock> getAll();

	/*
	 * 股票信息	 * */
	@Select(value = "select a.*,b.name as board_name FROM stock a LEFT JOIN stock_board b ON a.board_id = b.id where a.id = #{id}")
    Stock selectById(@Param("id")Long id);
}

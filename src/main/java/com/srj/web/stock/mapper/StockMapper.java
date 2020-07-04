package com.srj.web.stock.mapper;


import com.srj.web.datacenter.model.News;
import com.srj.web.stock.model.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface StockMapper extends tk.mybatis.mapper.common.Mapper<Stock>{
	@Select({"<script>",
			"SELECT a.id,a.CODE,a.NAME,a.industry,a.area,GROUP_CONCAT(c.name) as board_name FROM stock a",
			"LEFT JOIN stock_board_relate b on b.stock_id = a.id ",
			"LEFT JOIN stock_board c ON c.id = b.board_id",
			"where 1=1 ",
			"<when test='params.name!=null and params.name!=\"\" '>" ,
			"   and a.name like concat('%',#{params.name} ,'%') " ,
			"</when>",
			"<when test='params.code!=null and params.code!=\"\" '>" ,
			"   and a.code = #{params.code} " ,
			"</when>",
			"GROUP BY a.id ORDER BY a.id desc ",
			"</script>"})
	List<Stock> findPageInfo(@Param("params")Map<String, Object> params);

	String findStockIdByCode(String code);
	@Select(value = "select * FROM stock")
	List<Stock> getAll();

	/*
	 * 股票信息	 * */
	@Select({"<script>",
			"SELECT a.*,GROUP_CONCAT(c.id) as board_ids FROM stock a",
			"LEFT JOIN stock_board_relate b on b.stock_id = a.id ",
			"LEFT JOIN stock_board c ON c.id = b.board_id",
			"where a.id = #{id}",
			"</script>"})
    Stock selectById(@Param("id")Long id);
	/*
	 * 新闻列表	 * */
	@Select({"<script>",
			"SELECT a.id,a.title,a.source FROM news a ",
			"LEFT JOIN new_keyword b on b.new_id = a.id ",
			"LEFT JOIN keyword_board_relate c ON c.keyword_id = b.keyword_id ",
			"LEFT JOIN stock_board_relate d ON d.board_id = c.board_id ",
			"where d.stock_id = #{id} ",
			"GROUP BY a.id ORDER BY a.id desc limit 10",
			"</script>"})
    List<News> findStockNews(@Param("id")Long id);
}

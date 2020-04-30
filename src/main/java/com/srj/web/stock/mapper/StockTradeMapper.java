package com.srj.web.stock.mapper;

import com.srj.web.stock.model.StockTrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface StockTradeMapper extends tk.mybatis.mapper.common.Mapper<StockTrade>{
	@Select({"<script>",
			"SELECT a.*,b.name as stock_name,b.code as stock_code FROM stock_trade a " ,
			"LEFT JOIN stock b ON a.stock_id = b.id ",
			"where 1=1 ",
			"<when test='params.name!=null and params.name!=\"\" '>" ,
			"   and b.name = #{params.name} " ,
			"</when>",
			"<when test='params.trade_date!=null and params.trade_date!=\"\" '>" ,
			"   and a.time = #{trade_date} " ,
			"</when>",
			"ORDER BY id desc ",
			"</script>"})
	List<StockTrade> findPageInfo(@Param("params")Map<String, Object> params);

	Integer insertList(List<StockTrade> list);

	List<String> checkTradeData(String id);
	@Select(value = "SELECT * FROM stock_trade WHERE " +
			"trade_date = #{params.search_time} and stock_id = #{params.stock_id}")
	List<StockTrade> selectList(@Param("params")Map<String, Object> params);

    StockTrade selectNewestRecord();
	@Select(value = "select trade_date from stock_trade where stock_id = #{stock_id} ORDER BY trade_date desc limit 1")
	StockTrade selectLastTradeDate(@Param("stock_id")String stock_id);
}

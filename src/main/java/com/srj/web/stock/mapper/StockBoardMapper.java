package com.srj.web.stock.mapper;


import com.srj.web.stock.model.Stock;
import com.srj.web.stock.model.StockBoard;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StockBoardMapper extends tk.mybatis.mapper.common.Mapper<StockBoard>{

    @Select({"<script>",
            "SELECT id,NAME FROM stock_board ",
            "where 1=1 ",
            "<when test='params.name!=null and params.name!=\"\" '>" ,
            "   and name like concat('%',#{params.name} ,'%') " ,
            "</when>",
            "ORDER BY id desc ",
            "</script>"})
    List<StockBoard> findPageInfo(@Param("params") Map<String, Object> params);


    //删除中间表旧数据
    @Delete("DELETE FROM stock_board_relate where stock_id = #{id}")
    Integer deleteStockBoardRelate(@Param("id")Long id);

    //新增中间表数据
    @Insert({"<script>",
            "insert into stock_board_relate (stock_id,board_id) values ",
            "<foreach collection = 'list' item='record' separator=',' > ",
            " (#{stockId},#{record})",
            "</foreach></script>"})
    Integer insertStockBoardRelate(@Param("stockId")Long id,@Param("list")List<String> idList);
}

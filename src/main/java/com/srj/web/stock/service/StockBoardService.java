package com.srj.web.stock.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.stock.model.Stock;
import com.srj.web.stock.model.StockBoard;

import java.util.List;
import java.util.Map;

public interface StockBoardService {

	/*
	 * 分页显示股票列表
	 * */
	PageInfo<StockBoard> findPageInfo(Map<String, Object> params);
	/*
	 * 新增股票
	 * */
	Integer saveRecord(StockBoard record);
	/*
	 * 删除股票
	 * */
	Integer deleteRecord(Long id);
	/*
	 * 修改	 * */
	Integer editRecord(StockBoard record);

	//根据id查单条记录
    StockBoard getBoardById(Long id);
}

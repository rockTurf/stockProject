package com.srj.web.stock.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.utils.UUIDUtils;
import com.srj.web.datacenter.model.News;
import com.srj.web.stock.mapper.StockMapper;
import com.srj.web.stock.model.Stock;
import com.srj.web.stock.model.StockBoard;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public interface StockService {

	/*
	 * 分页显示股票列表
	 * */
	PageInfo<Stock> findPageInfo(Map<String, Object> params);
	/*
	 * 新增股票
	 * */
	Integer saveRecord(Stock record);
	/*
	 * 删除股票
	 * */
	Integer deleteRecord(Long id);
	/*
	 * 查找所有股票
	 * */
	List<Stock> getAllStock();
	//根据id取出单支股票信息
	Stock SelectRecordById(Long id);
	//根据code和股票名称取得id，如果没有就新增并返回id
	String getStockId(String code, String stockName) ;
	/*
	 * 查找所有板块	 * */
	List<StockBoard> getAllBoard();
	/*
	 * 股票信息	 * */
    Stock getStockById(Long id);
	/*
	 * 修改	 * */
	Integer editRecord(Stock record);
	/*
	 * 该股票新闻列表	 * */
    List<News> findStockNews(Long id);
}

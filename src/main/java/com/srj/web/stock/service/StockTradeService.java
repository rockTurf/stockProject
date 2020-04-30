package com.srj.web.stock.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.stock.model.StockTrade;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

public interface StockTradeService {

	//分页显示列表
	PageInfo<StockTrade> findPageInfo(Map<String, Object> params);
	Integer saveTxt(String filedata, HttpServletRequest req)throws IOException ;

	List<String> checkTradeData(String id);
	
	//进度条session
	String getProgress(HttpServletRequest req);

	/**
	 * 取出交易表最新日期的一条记录，做页面初始化参数来用
	 * */
    StockTrade getNewestRecord();
}

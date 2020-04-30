package com.srj.web.stock.service;

import com.alibaba.fastjson.JSONObject;


public interface StockDrawService {

	JSONObject selectDraw(String stock_id, String type, String search_time);

	//找到该股票最近一天的日期
	String selectLastTradeDate(String stock_id);

}

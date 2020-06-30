package com.srj.web.stock.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.utils.UUIDUtils;
import com.srj.web.stock.mapper.StockBoardMapper;
import com.srj.web.stock.mapper.StockMapper;
import com.srj.web.stock.model.Stock;
import com.srj.web.stock.model.StockBoard;
import com.srj.web.stock.service.StockBoardService;
import com.srj.web.stock.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StockBoardServiceImpl implements StockBoardService {

	@Resource
	private StockBoardMapper stockBoardMapper;
	/*
	 * 分页显示股票列表
	 * */
	@Override
	public PageInfo<StockBoard> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<StockBoard> list = stockBoardMapper.findPageInfo(params);
		return new PageInfo<StockBoard>(list);
	}
	/*
	 * 新增股票
	 * */
	@Override
	public Integer saveRecord(StockBoard record) {
		return stockBoardMapper.insertSelective(record);
	}
	/*
	 * 删除股票
	 * */
	@Override
	public Integer deleteRecord(Long id) {
		return stockBoardMapper.deleteByPrimaryKey(id);
	}

	//修改
	@Override
	public Integer editRecord(StockBoard record) {
		return stockBoardMapper.updateByPrimaryKey(record);
	}





}

package com.srj.web.stock.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.utils.UUIDUtils;
import com.srj.web.datacenter.model.News;
import com.srj.web.stock.mapper.StockBoardMapper;
import com.srj.web.stock.mapper.StockMapper;
import com.srj.web.stock.model.Stock;
import com.srj.web.stock.model.StockBoard;
import com.srj.web.stock.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class StockServiceImpl implements StockService {

	@Resource
	private StockMapper stockMapper;
	@Resource
	private StockBoardMapper stockBoardMapper;
	/*
	 * 分页显示股票列表
	 * */
	@Override
	public PageInfo<Stock> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Stock> list = stockMapper.findPageInfo(params);
		return new PageInfo<Stock>(list);
	}
	/*
	 * 新增股票
	 * */
	@Override
	public Integer saveRecord(Stock record) {
		record.setId(Long.parseLong(UUIDUtils.getRandomInteger(12)));
		return stockMapper.insertSelective(record);
	}
	/*
	 * 删除股票
	 * */
	@Override
	public Integer deleteRecord(Long id) {
		return stockMapper.deleteByPrimaryKey(id);
	}
	/*
	 * 查找所有股票
	 * */
	@Override
	public List<Stock> getAllStock(){
		List<Stock> list = stockMapper.getAll();
		return list;
	}
	//根据id取出单支股票信息
	@Override
	public Stock SelectRecordById(Long id) {
		return stockMapper.selectByPrimaryKey(id);
	}
	//根据code和股票名称取得id，如果没有就新增并返回id
	@Override
	public String getStockId(String code, String stockName) {
		String stock_id = stockMapper.findStockIdByCode(code);
		if(stock_id!=null){
			return stock_id;
		}
		//如果为空，则新增一个
		Long id = Long.parseLong(UUIDUtils.getRandomInteger(12));
		Stock stock = new Stock();
		stock.setId(id);
		stock.setCode(code);
		stock.setName(stockName);
		stockMapper.insertSelective(stock);
		return id.toString();
	}

	//详情
	@Override
	public Integer editRecord(Stock record) {
		int count = stockMapper.updateByPrimaryKey(record);

		//先把逗号分隔为list
		List<String> idList = Arrays.asList(record.getBoardIds().split(","));
		//更新股票-板块关系表
		stockBoardMapper.deleteStockBoardRelate(record.getId());
		stockBoardMapper.insertStockBoardRelate(record.getId(),idList);

		return count;
	}

	@Override
	public List<StockBoard> getAllBoard() {
		return stockBoardMapper.selectAll();
	}

	/*
	 * 股票信息	 * */
	@Override
	public Stock getStockById(Long id) {
		return stockMapper.selectById(id);
	}

	//新闻
	@Override
	public List<News> findStockNews(Long id) {
		return stockMapper.findStockNews(id);
	}


}

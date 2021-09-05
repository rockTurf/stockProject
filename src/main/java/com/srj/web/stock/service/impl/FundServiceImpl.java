package com.srj.web.stock.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysConstant;
import com.srj.common.utils.UUIDUtils;
import com.srj.web.datacenter.model.News;
import com.srj.web.stock.mapper.FundMapper;
import com.srj.web.stock.mapper.StockBoardMapper;
import com.srj.web.stock.mapper.StockMapper;
import com.srj.web.stock.model.Fund;
import com.srj.web.stock.model.Stock;
import com.srj.web.stock.model.StockBoard;
import com.srj.web.stock.service.FundService;
import com.srj.web.stock.tool.ExcelModelFundListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public class FundServiceImpl implements FundService {

	@Resource
	private FundMapper mapper;
	/*
	 * 分页显示列表
	 * */
	@Override
	public PageInfo<Fund> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Fund> list = mapper.findPageInfo(params);
		return new PageInfo<Fund>(list);
	}
	/**
	 * 新增数据集合（读取文件形式）
	 * */
	@Override
	public int addDataByFileList(List<String> fileList) {
		//循环
		for(String fileName:fileList){
			String readPath = SysConstant.TempUrl()+fileName;
			try {
				Sheet sheet = new Sheet(1,2,Fund.class);
				EasyExcelFactory.readBySax(new FileInputStream(readPath),sheet,new ExcelModelFundListener(mapper));

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}


}

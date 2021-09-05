package com.srj.web.stock.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.stock.model.Fund;

import java.util.List;
import java.util.Map;

public interface FundService {

	/**
	 * 分页显示列表
	 * */
	PageInfo<Fund> findPageInfo(Map<String, Object> params);
	/**
	 * 新增数据集合（读取文件形式）
	 * */
    int addDataByFileList(List<String> fileList);
}

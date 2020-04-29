package com.srj.web.datacenter.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.model.Keyword;
import com.srj.web.sys.model.SysUser;

import java.util.List;
import java.util.Map;

public interface KeywordService {
	
	/*
	 * 分页显示关键词
	 * */
	PageInfo<Keyword> findPageInfo(Map<String, Object> params);

	/*
	 * 保存关键字
	 * */
	int addKeyword(Map<String, Object> params, SysUser u);
	/*
	 * 检查是否有重复关键字(true==有，false==没有)
	 * */
	boolean checkKeyword(String name);

	//获取全部keyword
	List<Keyword> getAllKeyword();

	
	
	

}

package com.srj.web.datacenter.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.model.bbsMain;
import com.srj.web.sys.model.SysUser;

import java.util.Map;

public interface BBSMainService {
	
	/*
	 * 分页显示
	 * */
	PageInfo<bbsMain> findPageInfo(Map<String, Object> params);

	/*
	 * 保存
	 * */
	int addbbsMain(Map<String, Object> params, SysUser u);

	/*
	 * 详细信息
	 * */
    bbsMain getItemById(Long id);
}

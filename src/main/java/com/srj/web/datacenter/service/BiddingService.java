package com.srj.web.datacenter.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.model.Bidding;
import com.srj.web.sys.model.SysUser;

import java.util.Map;

public interface BiddingService {
	
	/*
	 * 分页显示招标信息
	 * */
	PageInfo<Bidding> findPageInfo(Map<String, Object> params);

	/*
	 * 保存招标信息
	 * */
	int addBidding(Map<String, Object> params, SysUser u);

	/*
	 * 详细信息
	 * */
    Bidding getItemById(Long id);
}

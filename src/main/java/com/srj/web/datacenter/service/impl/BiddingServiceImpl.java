package com.srj.web.datacenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.mapper.BiddingMapper;
import com.srj.web.datacenter.model.Bidding;
import com.srj.web.datacenter.service.BiddingService;
import com.srj.web.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BiddingServiceImpl implements BiddingService {
	
	@Autowired
	private BiddingMapper biddingMapper;

	
	/*
	 * 分页显示关键词
	 * */
	@Override
	public PageInfo<Bidding> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Bidding> list = biddingMapper.findPageInfo(params);
		return new PageInfo<Bidding>(list);
	}

	/*
	 * 保存关键字
	 * */
	@Override
	public int addBidding(Map<String, Object> params, SysUser u) {
		Bidding item = new Bidding();

		int count = biddingMapper.insertSelective(item);
		return count;
	}


	
	
	

}

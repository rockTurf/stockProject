package com.srj.web.datacenter.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.model.bbsReply;
import com.srj.web.sys.model.SysUser;

import java.util.Map;

public interface BBSReplyService {
	
	/*
	 * 分页显示
	 * */
	PageInfo<bbsReply> findPageInfo(Map<String, Object> params);

	/*
	 * 保存
	 * */
	int addbbsReply(Map<String, Object> params, SysUser u);

	/*
	 * 详细信息
	 * */
    bbsReply getItemById(Long id);

	/**
	 * 获取当前帖子的楼层数
	 * */
	int getMainFloor(String mainId);
}

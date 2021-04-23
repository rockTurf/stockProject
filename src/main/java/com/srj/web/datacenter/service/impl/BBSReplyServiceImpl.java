package com.srj.web.datacenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.web.datacenter.mapper.bbsReplyMapper;
import com.srj.web.datacenter.model.bbsReply;
import com.srj.web.datacenter.service.BBSReplyService;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BBSReplyServiceImpl implements BBSReplyService {
	
	@Autowired
	private bbsReplyMapper bbsReplyMapper;

	
	/*
	 * 分页显示
	 * */
	@Override
	public PageInfo<bbsReply> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<bbsReply> list = bbsReplyMapper.findPageInfo(params);
		//更新显示状态
		for(bbsReply item:list){
			String status = item.getStatus();
			if(status.equals(Constant.DEL_FLAG_NORMAL)){
				item.setStatus("正常");
			}else if(status.equals(Constant.DEL_FLAG_DELETE)){
				item.setStatus("已删除");
			}
		}
		return new PageInfo<bbsReply>(list);
	}

	/*
	 * 保存
	 * */
	@Override
	public int addbbsReply(Map<String, Object> params, SysUser u) {
		bbsReply item = new bbsReply();
		item.setUserId(u.getId().toString());
		item.setContent((String) params.get("content"));
		item.setCreateTime(DateUtils.getDateTime());
		item.setStatus(Constant.DEL_FLAG_NORMAL);
		int floor = getMainFloor((String)params.get("mainId"));
		item.setFloor(floor+1);
		int count = bbsReplyMapper.insertSelective(item);
		return count;
	}

	/*
	 * 详细信息
	 * */
	@Override
	public bbsReply getItemById(Long id) {
		return bbsReplyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 获取当前帖子的楼层数
	 * */
	@Override
	public int getMainFloor(String mainId) {
		bbsReply item = new bbsReply();
		item.setMainId(mainId);
		int n = bbsReplyMapper.selectCount(item);
		return n;
	}



}

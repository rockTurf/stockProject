package com.srj.web.datacenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.web.datacenter.mapper.bbsMainMapper;
import com.srj.web.datacenter.model.bbsMain;
import com.srj.web.datacenter.service.BBSMainService;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BBSMainServiceImpl implements BBSMainService {
	
	@Autowired
	private bbsMainMapper bbsMainMapper;

	
	/*
	 * 分页显示
	 * */
	@Override
	public PageInfo<bbsMain> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<bbsMain> list = bbsMainMapper.findPageInfo(params);
		//更新显示状态
		for(bbsMain item:list){
			String status = item.getStatus();
			if(status.equals(Constant.DEL_FLAG_NORMAL)){
				item.setStatus("正常");
			}else if(status.equals(Constant.DEL_FLAG_DELETE)){
				item.setStatus("已删除");
			}
		}
		return new PageInfo<bbsMain>(list);
	}

	/*
	 * 保存
	 * */
	@Override
	public int addbbsMain(Map<String, Object> params, SysUser u) {
		bbsMain item = new bbsMain();
		item.setUserId(u.getId().toString());
		item.setTitle((String) params.get("title"));
		item.setContent((String) params.get("content"));
		item.setCreateTime(DateUtils.getDateTime());
		item.setStatus(Constant.DEL_FLAG_NORMAL);

		int count = bbsMainMapper.insertSelective(item);
		return count;
	}

	/*
	 * 详细信息
	 * */
	@Override
	public bbsMain getItemById(Long id) {
		return bbsMainMapper.selectByPrimaryKey(id);
	}


}

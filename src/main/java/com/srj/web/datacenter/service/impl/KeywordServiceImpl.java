package com.srj.web.datacenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.mapper.KeywordMapper;
import com.srj.web.datacenter.model.Keyword;
import com.srj.web.datacenter.service.KeywordService;
import com.srj.web.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KeywordServiceImpl implements KeywordService {
	
	@Autowired
	private KeywordMapper keyMapper;

	
	/*
	 * 分页显示关键词
	 * */
	@Override
	public PageInfo<Keyword> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Keyword> list = keyMapper.findPageInfo(params);
		return new PageInfo<Keyword>(list);
	}

	/*
	 * 保存关键字
	 * */
	@Override
	public int addKeyword(Map<String, Object> params, SysUser u) {
		Keyword k = new Keyword();
		k.setName((String) params.get("name"));
		k.setCreateName(u.getName());
		int count = keyMapper.insertSelective(k);
		return count;
	}
	/*
	 * 检查是否有重复关键字(true==有，false==没有)
	 * */
	@Override
	public boolean checkKeyword(String name) {
		boolean b = true;
		Keyword key = keyMapper.checkKeyword(name);
		if(key!=null){
			b = false;
		}
		return b;
	}

	//获取全部keyword
	@Override
	public List<Keyword> getAllKeyword() {
		List<Keyword> list = keyMapper.getAllKeyword();
		return list;
	}

	
	
	

}

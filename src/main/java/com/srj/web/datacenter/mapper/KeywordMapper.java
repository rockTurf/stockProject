package com.srj.web.datacenter.mapper;

import com.srj.web.datacenter.model.Keyword;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@org.apache.ibatis.annotations.Mapper

public interface KeywordMapper extends Mapper<Keyword> {

	List<Keyword> findPageInfo(Map<String, Object> params);

	Keyword checkKeyword(Map<String, Object> params);

	List<Keyword> getAllKeyword();

	
}

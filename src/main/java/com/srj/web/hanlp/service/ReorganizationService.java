package com.srj.web.hanlp.service;

import com.srj.web.hanlp.model.Reorganization;

import java.util.List;
import java.util.Map;

public interface ReorganizationService {


	//查全部
	List<Reorganization> selectAll();

	//修改新闻-重整对应关系
    int editNewsReorg(Map<String, Object> params);
	//根据新闻id找出重整步骤
	Long selectByNewsId(Long id);
}

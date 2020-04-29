package com.srj.web.datacenter.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.model.Keyword;
import com.srj.web.datacenter.model.News;
import com.srj.web.sys.model.SysUser;
import java.util.*;

public interface NewsService {

	/*
	 * 分页显示列表
	 * */
	PageInfo<News> findPageInfo(Map<String, Object> params);

    void insertList(List<News> newsList);

    //增加
	int insertItem(News item);

	//查找
	News getById(Long id);

	//取出某网站最新一条记录
	List<News> selectBySource(String source);

	//取出新闻总量
    int getTotalNewsNumber();

      //取出新闻(一千条）
	List<News> getPageNewsOneK(int start,int size);

	//新闻和关键词进行匹配，插库
	Integer getInNewsKeyword(News news, Keyword key);

	//新增
	int saveArticle(News record, SysUser u);
}

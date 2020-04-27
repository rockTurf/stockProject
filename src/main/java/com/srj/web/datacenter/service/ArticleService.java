package com.srj.web.datacenter.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.model.Article;
import com.srj.web.sys.model.SysUser;

import java.util.List;
import java.util.Map;

public interface ArticleService {
	
	/*
	 * 分页显示关键词
	 * */
	PageInfo<Article> findPageInfo(Map<String, Object> params);
	/*
	 * 新增文章
	 * */
	int saveArticle(Article record, SysUser u);
	/*
	 * 删除文章
	 * */
	int deleteArticle(Long id);
	/**
	 * 根据作者id推送5条文章
	 * */
	List<Article> selectTop(Long userId);
	/**
	 * 查看最新文章
	 * */
    Article checkArticle(Long userId);
}

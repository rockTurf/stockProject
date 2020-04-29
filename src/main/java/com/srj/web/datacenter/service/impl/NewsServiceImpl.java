package com.srj.web.datacenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.mapper.KeywordMapper;
import com.srj.web.datacenter.mapper.NewsMapper;
import com.srj.web.datacenter.model.Keyword;
import com.srj.web.datacenter.model.News;
import com.srj.web.datacenter.service.NewsService;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.DateUtils;
import com.srj.web.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

	@Resource
	private NewsMapper newsMapper;
	@Resource
	private KeywordMapper keywordMapper;
	/*
	 * 分页显示列表
	 * */
	public PageInfo<News> findPageInfo(Map<String, Object> params) {
		//返回list
		List<News> list = new ArrayList<>();
		if(!StringUtil.isNullOrEmpty((String)params.get("title"))){
			//检索标题不为空，检索标题
			Keyword key = keywordMapper.checkKeyword((String)params.get("title"));
			//关键词为空，则返回空
			if(key==null){
				return new PageInfo<News>(list);
			}
			params.put("key_id",key.getId());
			////检索标题不为空，检索标题
			PageHelper.startPage(params);
			list = newsMapper.findPageInfoByKeyWord(params);
			return new PageInfo<News>(list);
		}else{
			////检索标题不为空，检索标题
			PageHelper.startPage(params);
			list = newsMapper.findPageInfo(params);
			return new PageInfo<News>(list);
		}
	}

      public void insertList(List<News> newsList) {
		for(News news:newsList){
			newsMapper.insert(news);
		}
      }

    //增加
	public int insertItem(News item) {
		return newsMapper.insert(item);
	}

	//查找
	public News getById(Long id) {
		return newsMapper.selectByPrimaryKey(id);
	}

	//取出某网站最新一条记录
	public List<News> selectBySource(String source) {
		return newsMapper.selectBySource(source);
	}

	//取出新闻总量
      public int getTotalNewsNumber() {
            return newsMapper.totalNewsNumber();
      }

      //取出新闻(一千条）
	public List<News> getPageNewsOneK(int start,int size) {
		return newsMapper.getPageNewsOneK(start,size);
	}

	//新闻和关键词进行匹配，插库
	public Integer getInNewsKeyword(News news, Keyword key) {
		//判断标题是否包含此关键词
		if(news.getTitle().indexOf(key.getName())!=-1){
			//如果包含，先查询下中间表是否已含有
			Long id = newsMapper.selectByNewIdAndKeyId(news.getId(),key.getId());
			if(id==null){
				//如果没有就插入
				return newsMapper.insertNewKeyword(news.getId(),key.getId());
			}
		}
		return 0;
	}

	//新增
	public int saveArticle(News record, SysUser u) {
		//来源
		record.setSource(u.getName()+"-手动添加");
		return newsMapper.insertSelective(record);
	}
}

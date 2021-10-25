package com.srj.web.datacenter.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.common.utils.SysConstant;
import com.srj.web.datacenter.mapper.KeywordMapper;
import com.srj.web.datacenter.mapper.NewsMapper;
import com.srj.web.datacenter.model.Keyword;
import com.srj.web.datacenter.model.News;
import com.srj.web.datacenter.service.NewsService;
import com.srj.web.stock.tool.ExcelModelNewsListener;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.DateUtils;
import com.srj.web.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

	@Resource
	private NewsMapper newsMapper;
	@Resource
	private KeywordMapper keywordMapper;

	//清理30天新闻到旧表
	public static final int CLEAR_NEWS_DATE = 30;
	/*
	 * 分页显示列表
	 * */
	public PageInfo<News> findPageInfo(Map<String, Object> params) {
		//返回list
		List<News> list = new ArrayList<>();
		if(!StringUtil.isNullOrEmpty((String)params.get("title"))){
			/*//检索标题不为空，检索标题
			Keyword key = keywordMapper.checkKeyword((String)params.get("title"));
			//关键词为空，则返回空
			if(key==null){
				return new PageInfo<News>(list);
			}
			params.put("key_id",key.getId());*/
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

	@Override
	public PageInfo<News> findUnusualPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		//返回list
		//先定义异常参数
		params.put("type", Constant.UNUSUAL_NEWS_TYPE);
		List<News> list = newsMapper.findPageUnusual(params);
		return new PageInfo<News>(list);
	}

	//条件增加
      public void insertList(List<News> newsList) {
		for(News news:newsList){
			News item = newsMapper.selectOne(news);
			if(item==null){
				newsMapper.insert(news);
			}
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

	//导入
	@Override
	public int addDataByFileList(List<String> fileList) {
		int n = 1;
		//循环
		for(String fileName:fileList){
			String readPath = SysConstant.TempUrl()+fileName;
			try {
				Sheet sheet = new Sheet(1,1, News.class);
				EasyExcelFactory.readBySax(new FileInputStream(readPath),sheet,new ExcelModelNewsListener(newsMapper));

			} catch (FileNotFoundException e) {
				n=0;
				e.printStackTrace();
			}
		}
		return n;
	}

	@Override
	public void clearNewsToOld() {
		//搜索超过30天的新闻条目
		List<News> oldList = newsMapper.selectByDate(CLEAR_NEWS_DATE);
		//将新闻存入旧新闻表,再从新表删除
		for(News item : oldList){
			int count = newsMapper.insertIntoHistory(item);
			newsMapper.delete(item);

		}
		String nowTime = DateUtils.getDateTime();
		System.out.println(nowTime+",共有"+oldList.size()+"条新闻被挪到旧库");
	}
}

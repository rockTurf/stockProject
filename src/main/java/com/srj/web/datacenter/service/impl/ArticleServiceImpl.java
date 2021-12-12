package com.srj.web.datacenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.common.tools.Pdf2TextTool;
import com.srj.common.tools.SnowflakeSequence;
import com.srj.common.utils.SysConstant;
import com.srj.web.datacenter.mapper.ArticleMapper;
import com.srj.web.datacenter.model.Article;
import com.srj.web.datacenter.service.ArticleService;
import com.srj.web.sys.model.SysFile;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysFileService;
import com.srj.web.util.DateUtils;
import com.srj.web.util.StringUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private SysFileService sysFileService;
	
	/*
	 * 分页显示关键词
	 * */
	public PageInfo<Article> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		//拆分关键词
		if(ObjectUtils.isNotEmpty(params.get("keyword"))){
			String keywords = (String)params.get("keyword");
			String[] array = keywords.split(" ");
			params.put("array",array);
		}
		List<Article> list = articleMapper.findPageInfo(params);
		//存入附件
		for(Article a:list){
			List<SysFile> fileList = sysFileService.selectByParams(Constant.FILE_FLAG_ARTICLE,a.getId());
			a.setFileList(fileList);
		}
		return new PageInfo<Article>(list);
	}

	/*
	 * 新增文章
	 * */
	public int saveArticle(Map<String, Object> params,Article record, SysUser u) {
		if (StringUtils.isEmpty((String)params.get("filepath"))){
			return 0;
		}
		int count = 0;
		//分解附件表
		List<String> fileMap = StringUtil.String2List((String) params.get("filepath"));
		SnowflakeSequence idWorker = new SnowflakeSequence();
		//遍历附件表
		for(String one:fileMap){
			//生成id
			long record_id = idWorker.nextId();
			String[] array = one.split("=");
			record.setId(record_id);
			record.setTitle(array[0]);
			//先把文章添加到文章表
			record.setCreateName(u.getName());
			record.setCreateTime(DateUtils.getDateTime());
			//文件路径
			String fileUrl = SysConstant.UploadUrl()+array[1];
			//文章内容
			String content = Pdf2TextTool.PDF2String(fileUrl);
			record.setContent(content);
			count = articleMapper.insertSelective(record);
			//存入附件
			sysFileService.saveFile(Constant.FILE_FLAG_ARTICLE, record_id, one, u);
		}

		return count;
	}
	/*
	 * 删除文章
	 * */
	public int deleteArticle(Long id) {
		int count = articleMapper.deleteByPrimaryKey(id);
		if(count>0){
			//删除article_keyword中间表
			//articleMapper.deleteArticleKeyword(id);
			//删除附件表数据
			sysFileService.deleteFile(Constant.FILE_FLAG_ARTICLE,id);
		}
		return count;
	}
	/**
	 * 根据作者id推送5条文章
	 * */
	public List<Article> selectTop(Long userId) {
		List<Article> list = articleMapper.selectTop();
		return list;
	}

	/**
	 * 查看最新文章
	 * */
    public Article checkArticle(Long userId) {
		Article item = articleMapper.selectTopOne();
		long last = DateUtils.StringToDate(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss").getTime();
		long now = System.currentTimeMillis();
		long time = now-last;
		System.out.println("现在时间："+ DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+",Time值："+time);
		if(time<=2200){
			return item;
		}
		return null;
    }

    /**
	 * 根据文章ID搜索
	 * */
	@Override
	public Article getById(Long id) {
		Article article = articleMapper.selectByPrimaryKey(id);
		return article;
	}
}

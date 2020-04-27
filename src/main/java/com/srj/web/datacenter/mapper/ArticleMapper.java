package com.srj.web.datacenter.mapper;

import com.srj.web.datacenter.model.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@org.apache.ibatis.annotations.Mapper
public interface ArticleMapper extends Mapper<Article> {

	List<Article> findPageInfo(Map<String, Object> params);

	Integer insertArticleKeyword(Article article);

	Integer deleteArticleKeyword(Long id);

	List<Article> selectTop();
	
	Article selectTopOne();

}

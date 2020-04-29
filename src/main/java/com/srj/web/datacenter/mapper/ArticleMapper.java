package com.srj.web.datacenter.mapper;

import com.srj.web.datacenter.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@org.apache.ibatis.annotations.Mapper
public interface ArticleMapper extends Mapper<Article> {

	@Select({ "<script>",
			"select a.id,a.title,a.introduction,a.create_name,a.create_time,GROUP_CONCAT(c.name) as keywords " ,
			"from article a ",
			"LEFT JOIN article_keyword b ON a.id = b.a_id ",
			"LEFT JOIN keyword c ON b.k_id = c.id",
			"where 1=1 ",
			"<when test='params.title!=null and params.title!=\"\" '>" ,
			"   and a.title like concat('%',#{params.title} ,'%') " ,
			"</when>",
			"<when test='params.keyword!=null and params.keyword!=\"\" '>" ,
			"   and c.name like concat('%',#{params.keyword} ,'%') " ,
			"</when>",
			"<when test='params.introduction!=null and params.introduction!=\"\" '>" ,
			"   and a.introduction like concat('%',#{params.introduction} ,'%') " ,
			"</when>",
			"GROUP BY a.id ORDER BY a.id DESC ",
			"</script>"})
	List<Article> findPageInfo(@Param("params")Map<String, Object> params);

	//插入article_keyword中间表
	@Insert({"<script>",
			"insert IGNORE into article_keyword (a_id,k_id) values ",
			"<foreach collection = 'article.kids' item='kid' separator=',' > ",
			" (#{article.id},#{kid})",
			"</foreach></script>"})
	Integer insertArticleKeyword(@Param("article")Article article);

	Integer deleteArticleKeyword(Long id);

	List<Article> selectTop();
	
	Article selectTopOne();

}

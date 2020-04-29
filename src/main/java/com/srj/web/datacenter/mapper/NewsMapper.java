package com.srj.web.datacenter.mapper;


import com.srj.web.datacenter.model.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@org.apache.ibatis.annotations.Mapper
public interface NewsMapper extends Mapper<News> {

	@Select(value = "SELECT * from news ORDER BY news_time DESC")
	List<News> findPageInfo(@Param("params")Map<String, Object> params);
	@Select({"<script>",
			"SELECT a.* FROM news a LEFT JOIN new_keyword b ON a.id = b.new_id",
			"   where b.keyword_id = #{params.key_id} ORDER BY a.news_time DESC" ,
			"</script>"})
	List<News> findPageInfoByKeyWord(@Param("params")Map<String, Object> params);

	@Insert({"<script>",
			"insert IGNORE into new_keyword (new_id,keyword_id) values ",
			"(#{new_id},#{keyword_id})",
			"</script>"})
	int insertNewKeyword(@Param("new_id")Long new_id,@Param("keyword_id")Long keyword_id);
	//<!--取出新闻总数量-->
	@Select(value = "SELECT count(*) from news")
	int totalNewsNumber();
	//<!-- 一千条循环取新闻 -->
	@Select(value = "SELECT * FROM news limit #{start},#{size}")
	List<News> getPageNewsOneK(@Param("start")int start,@Param("size")int size);

	//<!-- 根据new_id,keyword_id-->
	@Select(value = "SELECT id from new_keyword where new_id=#{new_id} and keyword_id=#{keyword_id}")
	Long selectByNewIdAndKeyId(@Param("new_id")Long new_id,@Param("keyword_id")Long keyword_id);
	//<!-- 按照来源倒叙排列 -->
	@Select(value = "SELECT * from news where source = #{source} ORDER BY news_time DESC limit 40")
	List<News> selectBySource(@Param("source")String source);


}

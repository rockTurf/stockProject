package com.srj.web.datacenter.model;

import com.srj.web.sys.model.SysFile;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
public class Article {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;//文章标题
	private String introduction;//文章简介
	private String content;//文章内容
	private String createName;

	@Transient
	private String createTime;
	
	
	@Transient
	private List<SysFile> fileList;
	@Transient
	private Long[] kids;//关键词列表
	
	@Transient
	private String keywords;//关键词字符串
}

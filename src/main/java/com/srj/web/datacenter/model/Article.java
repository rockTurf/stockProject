package com.srj.web.datacenter.model;

import com.srj.web.sys.model.SysFile;

import javax.persistence.*;
import java.util.List;
@Entity
public class Article {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;//文章标题
	private String introduction;//文章简介
	private String createName;

	@Transient
	private String createTime;
	
	
	@Transient
	private List<SysFile> fileList;
	@Transient
	private Long[] kids;//关键词列表
	
	@Transient
	private String keywords;//关键词字符串

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<SysFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<SysFile> fileList) {
		this.fileList = fileList;
	}

	public Long[] getKids() {
		return kids;
	}

	public void setKids(Long[] kids) {
		this.kids = kids;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}

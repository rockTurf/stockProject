package com.srj.web.datacenter.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class News extends BaseRowModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ExcelProperty(index = 2)
	private String title;//标题
	private String content;//内容
	@ExcelProperty(index = 3)
	private String source;//来源
	@ExcelProperty(index = 4)
	private String author;//作者
	@ExcelProperty(index = 1)
	private String newsTime;//新闻时间
	@ExcelProperty(index = 5)
	private String address;//地址
	@Transient
	private String createTime;//创建时间

}

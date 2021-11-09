package com.srj.web.stock.model;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Stock extends BaseRowModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ExcelProperty(index = 0)
	private String code;//股票编码
	@ExcelProperty(index = 1)
	private String name;//股票名称
	@ExcelProperty(index = 2)
	private String industry;//细分行业
	@ExcelProperty(index = 3)
	private String area;//地区

	@Transient
	private String boardIds;//板块id
	@Transient
	private String boardName;//板块名称


}

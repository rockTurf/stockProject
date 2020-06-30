package com.srj.web.stock.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Stock{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;//股票编码
	private String name;//股票名称
	private String industry;//细分行业
	private String area;//地区

	@Transient
	private String boardIds;//板块id
	@Transient
	private String boardName;//板块名称


}

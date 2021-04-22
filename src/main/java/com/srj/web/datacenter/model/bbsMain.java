package com.srj.web.datacenter.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class bbsMain {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;//用户id
	private String title;//标题
	private String content;//内容
	private String createTime;
	private String status;//状态


}

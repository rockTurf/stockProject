package com.srj.web.datacenter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class bbsReply {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;//用户id
	private String mainId;//主贴id
	private int floor;//楼层
	private String content;//内容
	private String createTime;
	private String status;//状态


}

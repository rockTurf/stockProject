package com.srj.web.stock.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StockBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;//股票名称

	@Transient
	private String keywordIds;//板块id
}

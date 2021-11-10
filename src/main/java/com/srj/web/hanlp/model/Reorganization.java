package com.srj.web.hanlp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Reorganization {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String step;//步骤
	private String name;//步骤名
	private String weight;//权重


}

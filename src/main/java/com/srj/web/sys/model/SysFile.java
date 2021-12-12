package com.srj.web.sys.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SysFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tableId;//连接表id

    private String flag;//标识

    private String filename;//文件名

    private String fileurl;//实际地址

    private String md5;//md5值

    private String createName;

    private String createTime;

}
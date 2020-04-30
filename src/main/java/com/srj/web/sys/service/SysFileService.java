package com.srj.web.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.srj.web.sys.mapper.SysFileMapper;
import com.srj.web.sys.model.SysFile;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.DateUtils;
import com.srj.web.util.StringUtil;

public interface SysFileService {

	/*
	 * 附件上传公共方法
	 * 参数：数据表名参数，数据id，上传文件名，真实文件名
	 * */
	int saveFile(String flag,Long table_id,String fileArray,SysUser u);
	
	//根据文件内容返回文件有多少个.
	int countFile(String str);

	//根据表标识符号和表id查询附件列表
	List<SysFile> selectByParams(String flag, Long id);
	/*
	 * 删除附件表
	 * */
	Integer deleteFile(String flag, Long id);
	

}

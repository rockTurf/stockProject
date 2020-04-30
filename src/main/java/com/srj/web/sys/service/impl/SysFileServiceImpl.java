package com.srj.web.sys.service.impl;

import com.srj.web.sys.mapper.SysFileMapper;
import com.srj.web.sys.model.SysFile;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysFileService;
import com.srj.web.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysFileServiceImpl implements SysFileService {
	
	@Resource
	private SysFileMapper sysFileMapper;
	
	/*
	 * 附件上传公共方法
	 * 参数：数据表名参数，数据id，上传文件名，真实文件名
	 * */
	public int saveFile(String flag,Long table_id,String fileArray,SysUser u){
		int count = 0;
		List<String> fileMap = StringUtil.String2List(fileArray);
		List<SysFile> listrecord = new ArrayList<SysFile>();
		String[] array = null;
			if(fileArray!=null&&!fileArray.isEmpty()){
				for(String s:fileMap){
					array = s.split("=");
					SysFile record = new SysFile();
					record.setFilename(array[0]);
					record.setFileurl(array[1]);
					record.setTableId(table_id);
					record.setFlag(flag);
					record.setCreateName(u.getName());
					//record.setCreateTime(DateUtils.formatDateTime(new Date()));
					listrecord.add(record);
				}
				count = sysFileMapper.saveFileList(listrecord);
			}
		return count;
	}
	
	//根据文件内容返回文件有多少个.
	public int countFile(String str){
		int count = 0;
		if(str==null||"".equals(str)){
			count = 0;
		}else{
			String array[] = str.split(",");
			count = array.length;
		}
		return count;
	}

	//根据表标识符号和表id查询附件列表
	public List<SysFile> selectByParams(String flag, Long id) {
		SysFile record = new SysFile();
		record.setTableId(id);
		record.setFlag(flag);
		return sysFileMapper.select(record);
	}

	/*
	 * 删除附件表
	 * */
	public Integer deleteFile(String flag, Long id) {
		SysFile record = new SysFile();
		record.setTableId(id);
		record.setFlag(flag);
		return sysFileMapper.delete(record);
	}
	

}

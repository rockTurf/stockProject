package com.srj.web.tool;

import com.srj.common.utils.FileUtil;
import com.srj.common.utils.SysConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("download")
public class DownloadController {

	@RequestMapping("model")
	public void model(String filename, String model, HttpServletResponse response, HttpServletRequest request) throws Exception{
		 //模板列表
		 if("stockPrice".equals(model)){
			 filename="行情模板.xlsx";
		 }else if("stockTrade".equals(model)){
			 filename="交易模板 .xlsx";
		 }
		File previewFile = new File(SysConstant.DownloadUrl()+filename);
		FileUtil.downloadFile(filename, response, previewFile);
	}
	
	
	//附件下载
	@RequestMapping("file")
	public void downLoadFile(String fileName, String fileUrl,
                             HttpServletResponse response, HttpServletRequest request) throws Exception{
		File previewFile = new File(SysConstant.UploadUrl()+fileUrl);
		//取得文件名
		/*
		 * String temp[] = fileUrl.replaceAll("\\\\","/").split("/"); if (temp.length >
		 * 1) { fileUrl = temp[temp.length - 1]; }
		 */
		fileUrl = fileName;
		FileUtil.downloadFile(fileUrl, response, previewFile);
	}


	

}

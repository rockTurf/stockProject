package com.srj.web.stock.controller;

import com.github.pagehelper.PageInfo;
import com.srj.web.stock.model.Fund;
import com.srj.web.stock.service.FundService;
import com.srj.web.tools.FileTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("fund")
public class FundController {

	@Resource
	private FundService fundService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model, Map<String, Object> params){
		return "datacenter/stock/fund-manager";
	}

	/**
	 * 分页显示列表
	 *
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<Fund> page = fundService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/stock/fund-list";
	}


	/**
	 * 导入数据
	 *
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addFundData", method = RequestMethod.POST)
	public @ResponseBody String addFundData(@RequestParam Map<String, Object> params, Model model) {
		//取到文件名称
		String fileData = params.get("fileData").toString();
		//获取文件List
		List<String> fileList = FileTools.GetFileListByMapString(fileData);
		int count = fundService.addDataByFileList(fileList);
		return "success";
	}


}

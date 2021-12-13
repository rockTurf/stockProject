package com.srj.web.datacenter.controller;

import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.model.News;
import com.srj.web.datacenter.service.NewsService;
import com.srj.web.tools.FileTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("news")
public class NewsController {

	@Resource
	private NewsService newsService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model, Map<String, Object> params){
		model.addAttribute("params", params);
		return "datacenter/news/news-manager";
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
		PageInfo<News> page = newsService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/news/news-list";
	}
	
	
	/**
	 * 跳转编辑角色页面
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String showDetail(Long id, @RequestParam Map<String, Object> params, Model model){
		News news = newsService.getById(id);
		model.addAttribute("news", news);
		return "datacenter/news/news-detail";
	}
	

	/**
	 * 导入数据
	 *
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addNewsData", method = RequestMethod.POST)
	public @ResponseBody Integer addNewsData(@RequestParam Map<String, Object> params, Model model) {
		//取到文件名称
		String fileData = params.get("fileData").toString();
		//获取文件List
		List<String> fileList = FileTools.GetFileListByMapString(fileData);
		int count = newsService.addDataByFileList(fileList);
		return count;
	}
	
}

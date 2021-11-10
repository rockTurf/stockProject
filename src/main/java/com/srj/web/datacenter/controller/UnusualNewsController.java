package com.srj.web.datacenter.controller;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.model.News;
import com.srj.web.datacenter.service.NewsService;
import com.srj.web.hanlp.model.Reorganization;
import com.srj.web.hanlp.service.ReorganizationService;
import com.srj.web.sys.model.SysUser;
import com.srj.web.tool.FileTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("unusualNews")
public class UnusualNewsController {

	@Resource
	private NewsService newsService;
	@Resource
	private ReorganizationService reorganizationService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model, Map<String, Object> params){
		model.addAttribute("params", params);
		return "datacenter/unusual-news/news-manager";
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
		PageInfo<News> page = newsService.findUnusualPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/unusual-news/news-list";
	}
	
	
	/**
	 * 跳转编辑页面
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	public String showDetail(Long id, @RequestParam Map<String, Object> params, Model model){
		News news = newsService.getById(id);
		model.addAttribute("news", news);
		return "datacenter/unusual-news/news-detail";
	}

	/**
	 * 跳转重整选择页面
	 *
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "reorg", method = RequestMethod.POST)
	public String showReorg(Long id, @RequestParam Map<String, Object> params, Model model){
		News news = newsService.getById(id);
		List<Reorganization> reorgList = reorganizationService.selectAll();
		//对应上
		Long reorg_id = reorganizationService.selectByNewsId(id);
		model.addAttribute("news", news);
		model.addAttribute("reorg_id", reorg_id);
		model.addAttribute("reorgList", reorgList);
		return "datacenter/unusual-news/news-reorg";
	}
	
}

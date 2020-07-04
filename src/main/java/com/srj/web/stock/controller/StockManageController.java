package com.srj.web.stock.controller;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.model.News;
import com.srj.web.stock.model.Stock;
import com.srj.web.stock.model.StockBoard;
import com.srj.web.stock.service.StockService;
import com.srj.web.sys.model.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stock")
public class StockManageController {
	
	@Resource
	private StockService stockService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model, Map<String, Object> params){
		//板块列表
		List<StockBoard> boardList = stockService.getAllBoard();
		model.addAttribute("boardList", boardList);
		return "datacenter/stock/stock-manager";
	}
	
	/**
	 * 分页显示股票列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<Stock> page = stockService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/stock/stock-list";
	}
	/**
	 * 新增
	 */
	@RequestMapping(value = "save")
	public @ResponseBody
    Integer save(@ModelAttribute Stock record, @RequestParam Map<String, Object> params,
                 Model model, HttpServletRequest request, HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser(request);
		return stockService.saveRecord(record);
	}
	/**
	 * 详情
	 */
	@RequestMapping(value = "detail")
	public String detail(Long id,Model model){
		//股票信息
		Stock stock = stockService.getStockById(id);
		//板块列表
		List<StockBoard> boardList = stockService.getAllBoard();
		model.addAttribute("stock", stock);
		model.addAttribute("boardList", boardList);
		return "datacenter/stock/stock-detail";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete")
	public @ResponseBody
    Integer delete(@RequestParam Long id, Model model, HttpServletRequest request, HttpServletResponse response){
		//删除文章
		int count = stockService.deleteRecord(id);
		return count;
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "edit")
	public @ResponseBody
	Integer edit(@ModelAttribute Stock record, @RequestParam Map<String, Object> params,
				 Model model, HttpServletRequest request, HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser(request);
		return stockService.editRecord(record);
	}

	/**
	 * 新闻列表
	 */
	@RequestMapping(value = "news")
	public String stockNews(@RequestParam Long id,Model model){
		List<News> list = stockService.findStockNews(id);
		model.addAttribute("list", list);
		return "datacenter/stock/stock-news";
	}
}

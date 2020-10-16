package com.srj.web.stock.controller;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.model.Keyword;
import com.srj.web.datacenter.service.KeywordService;
import com.srj.web.stock.model.Stock;
import com.srj.web.stock.model.StockBoard;
import com.srj.web.stock.service.StockBoardService;
import com.srj.web.stock.service.StockService;
import com.srj.web.sys.model.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stockBoard")
public class StockBoardController {
	
	@Resource
	private StockBoardService service;
	@Resource
	private KeywordService keywordService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model, Map<String, Object> params){
		return "datacenter/stock/stockBoard-manager";
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
		PageInfo<StockBoard> page = service.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/stock/stockBoard-list";
	}
	/**
	 * 新增
	 */
	@RequestMapping(value = "save")
	public @ResponseBody
    Integer save(@ModelAttribute StockBoard record, @RequestParam Map<String, Object> params,
                 Model model, HttpServletRequest request, HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser(request);
		return service.saveRecord(record);
	}
	/**
	 * 详情
	 */
	@RequestMapping(value = "detail")
	public String detail(Long id,Model model){
		//信息
		StockBoard record = service.getBoardById(id);
		//取出全部关键词
		List<Keyword> keywordList = keywordService.getAllKeyword();
		model.addAttribute("keywordList", keywordList);
		model.addAttribute("record", record);
		return "datacenter/stock/stockBoard-detail";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete")
	public @ResponseBody
    Integer delete(@RequestParam Long id, Model model, HttpServletRequest request, HttpServletResponse response){
		//删除
		int count = service.deleteRecord(id);
		return count;
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "edit")
	public @ResponseBody
	Integer edit(@ModelAttribute StockBoard record, @RequestParam Map<String, Object> params,
				 Model model, HttpServletRequest request, HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser(request);
		return service.editRecord(record);
	}
}

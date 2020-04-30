package com.srj.web.stock.controller;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.stock.model.Stock;
import com.srj.web.stock.service.StockService;
import com.srj.web.sys.model.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		model.addAttribute("params", params);
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
	 * 新增股票
	 */
	@RequestMapping(value = "save")
	public @ResponseBody
    Integer save(@ModelAttribute Stock record, @RequestParam Map<String, Object> params,
                 Model model, HttpServletRequest request, HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser(request);
		return stockService.saveRecord(record);
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
}

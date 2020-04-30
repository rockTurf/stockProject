package com.srj.web.stock.controller;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.stock.model.Stock;
import com.srj.web.stock.model.StockTrade;
import com.srj.web.stock.service.StockService;
import com.srj.web.stock.service.StockTradeService;
import com.srj.web.sys.model.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stockTrade")
public class StockTradeController {


	@Resource
	private StockService stockService;
	@Resource
	private StockTradeService stockTradeService;

	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model, Map<String, Object> params){
		List<Stock> stockList = stockService.getAllStock();
		model.addAttribute("stockList", stockList);
		return "datacenter/stock/stockTrade-manager";
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
		PageInfo<StockTrade> page = stockTradeService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/stock/stockTrade-list";
	}
	
	/**
	 * 通过上传的添加行情列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addStockTrade")
	public @ResponseBody
    Integer addByExcel(@RequestParam String filedata, Model model, HttpServletRequest req) throws IOException, ParseException{
		int count = stockTradeService.saveTxt(filedata,req);
		//如果成功，执行计算的存储过程
		//stockDataService.CallProcedure();
		
		return count;
	}
	/**
	 * 检查数据完整性
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "checkTradeData")
	public @ResponseBody
    List<String> checkTradeData(@RequestParam String id){
		List<String> list = stockTradeService.checkTradeData(id);
		return list;
	}
	/**
	 * 读取session数据（进度条）
	 * 
	 * @return
	 */
	@RequestMapping(value = "getProgress")
	public @ResponseBody
    String getProgress(HttpServletRequest req){
		return stockTradeService.getProgress(req);
	}
	
}

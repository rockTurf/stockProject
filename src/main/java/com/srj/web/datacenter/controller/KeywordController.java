package com.srj.web.datacenter.controller;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.model.Keyword;
import com.srj.web.datacenter.service.KeywordService;
import com.srj.web.sys.model.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@Transactional
@RequestMapping("keyword")
public class KeywordController {

	@Resource
	private KeywordService keyService;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model, Map<String, Object> params){
		model.addAttribute("params", params);
		return "datacenter/keyword/keyword-manager";
	}
	/**
	 * 分页显示关键词列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<Keyword> page = keyService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/keyword/keyword-list";
	}
	/**
	 * 新增关键词
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody
    Integer save(@RequestParam Map<String,Object> params, Model model, HttpServletRequest request){
		SysUser u = SysUserUtil.getSessionLoginUser(request);
		int count = keyService.addKeyword(params,u);
		return count;
	}
	/**
	 * 检验关键词名
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/checkKeyword")
	public @ResponseBody
    Integer checkKeyword(@RequestParam String name){
		boolean b= keyService.checkKeyword(name);
		int count = 1;
		//数据库取到对应的用户信息不为空 判断
		if(b==true){
			count = 0;
		}
		return count;
	}
}

package com.srj.web.datacenter.controller;

import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.model.Article;
import com.srj.web.datacenter.model.Keyword;
import com.srj.web.datacenter.service.ArticleService;
import com.srj.web.datacenter.service.KeywordService;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysFileService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
@RequestMapping("article")
public class ArticleController {
	
	@Resource
	private ArticleService articleService;
	@Resource
	private KeywordService keywordService;
	@Resource
	private SysFileService sysFileService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model, Map<String, Object> params){
		//关键词列表
		List<Keyword> klist = keywordService.getAllKeyword();
		params.put("klist", klist);
		model.addAttribute("params", params);
		return "datacenter/article/article-manager";
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
		PageInfo<Article> page = articleService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/article/article-list";
	}
	
	/**
	 * 新增文章
	 */
	@RequestMapping(value = "save")
	public @ResponseBody
    Integer save(@ModelAttribute Article record, @RequestParam Map<String, Object> params, Model model, HttpServletRequest request, HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser(request);
		//新增文章
		int count = articleService.saveArticle(record,u);
		//存入附件
		sysFileService.saveFile(Constant.FILE_FLAG_ARTICLE, record.getId(), (String) params.get("filepath"), u);
		return count;
	}
	
	/**
	 * 删除文章
	 */
	@RequestMapping(value = "delete")
	public @ResponseBody
    Integer delete(@RequestParam Long id, Model model, HttpServletRequest request, HttpServletResponse response){
		//删除文章
		int count = articleService.deleteArticle(id);
		return count;
	}

	/**
	 * 推送文章
	 */
	@RequestMapping(value = "push")
	public @ResponseBody
    List<Article> pushArticle(@RequestParam Long userId){
		//根据用户id发出推送文章
		List<Article> list = articleService.selectTop(userId);

		return list;
	}
	
	/**
	 * 扫描最新文章
	 */
	@RequestMapping(value = "lastst")
	public @ResponseBody
    HashMap<String,Object> checkArticle(@RequestParam Long userId){
		//根据用户id发出推送文章
		Article item = articleService.checkArticle(userId);
		HashMap<String,Object> map = new HashMap<String,Object>();
		if(item==null){
			map.put("code",0);
			return map;
		}else {//有返回
			map.put("code",1);
			map.put("id",item.getId());
			map.put("title",item.getTitle());
			return map;
		}
		
	}
}

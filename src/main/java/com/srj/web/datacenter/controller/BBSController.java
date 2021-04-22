package com.srj.web.datacenter.controller;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.model.bbsMain;
import com.srj.web.datacenter.service.BBSMainService;
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
import java.util.Map;

@Controller
@Transactional
@RequestMapping("bbs")
public class BBSController {

    @Resource
    private BBSMainService bbsMainService;

    /**
     * 跳转到页面
     */
    @RequestMapping
    public String toPage(Model model, Map<String, Object> params){
        model.addAttribute("params", params);
        return "datacenter/bbs/bbsMain/bbsMain-manager";
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
        PageInfo<bbsMain> page = bbsMainService.findPageInfo(params);
        model.addAttribute("page", page);
        return "datacenter/bbs/bbsMain/bbsMain-list";
    }
    /**
     * 新增关键词
     */
    @RequestMapping(value = "/save")
    public @ResponseBody
    Integer save(@RequestParam Map<String,Object> params, Model model, HttpServletRequest request){
        SysUser u = SysUserUtil.getSessionLoginUser(request);
        int count = bbsMainService.addbbsMain(params,u);
        return count;
    }


    /**
     * 跳转编辑角色页面
     *
     * @param params
     * @param model
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public String showDetail(Long id, @RequestParam Map<String, Object> params, Model model){
        bbsMain item = bbsMainService.getItemById(id);
        model.addAttribute("item", item);
        return "datacenter/bbs/bbsMain/bbsMain-detail";
    }
}

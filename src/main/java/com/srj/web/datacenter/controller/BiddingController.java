package com.srj.web.datacenter.controller;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.model.Bidding;
import com.srj.web.datacenter.service.BiddingService;
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
@RequestMapping("bidding")
public class BiddingController {

    @Resource
    private BiddingService biddingService;

    /**
     * 跳转到页面
     */
    @RequestMapping
    public String toPage(Model model, Map<String, Object> params){
        model.addAttribute("params", params);
        return "datacenter/bidding/bidding-manager";
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
        PageInfo<Bidding> page = biddingService.findPageInfo(params);
        model.addAttribute("page", page);
        return "datacenter/bidding/bidding-list";
    }
    /**
     * 新增关键词
     */
    @RequestMapping(value = "/save")
    public @ResponseBody
    Integer save(@RequestParam Map<String,Object> params, Model model, HttpServletRequest request){
        SysUser u = SysUserUtil.getSessionLoginUser(request);
        int count = biddingService.addBidding(params,u);
        return count;
    }
}

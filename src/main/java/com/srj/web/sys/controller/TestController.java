package com.srj.web.sys.controller;

import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/hello")
    public @ResponseBody String hello(){
        SysUser user = sysUserService.CheckUser("admin");
        return user.getName();
    }
    @RequestMapping("/test")
    public String test(){
        SysUser user = sysUserService.CheckUser("admin");
        return "test";
    }
}

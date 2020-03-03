package com.srj.web.sys.controller;

import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@ResponseBody
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/hello")
    public String hello(){
        SysUser user = sysUserService.CheckUser("admin");
        return user.getName();
    }
}

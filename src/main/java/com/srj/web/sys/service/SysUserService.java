package com.srj.web.sys.service;

import com.github.pagehelper.PageInfo;
import com.srj.web.sys.model.SysUser;

import java.util.Map;


public interface SysUserService {

	SysUser CheckUser(String loginName);
	//校验密码
	boolean CheckPassword(SysUser user,String loginPwd);
	//显示用户列表
	PageInfo<SysUser> findPageInfo(Map<String, Object> params);
	//增加用户
	int addUser(SysUser user);
	//根据id查找用户
	SysUser getUserById(Long id);
	//修改用户
    int editUser(SysUser record);
}

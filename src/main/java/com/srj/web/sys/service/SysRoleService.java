package com.srj.web.sys.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.sys.mapper.SysRoleMapper;
import com.srj.web.sys.model.SysRole;

public interface SysRoleService {

	//分页显示角色列表
	PageInfo<SysRole> findPageInfo(Map<String, Object> params);
	//所有角色名
	List<SysRole> getAllRole();
	//新增角色
	int addRole(SysRole record);
	//修改角色
	int editRole(SysRole record);
	//根据id取出角色信息
	SysRole getRoleById(Long id);
	//返回已拥有的权限信息
	List<Long> getRoleResourceById(Long id);
	//保存角色的权限
	int saveRoleResource(Long id,String resIds);
}

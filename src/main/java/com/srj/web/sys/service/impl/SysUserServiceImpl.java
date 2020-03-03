package com.srj.web.sys.service.impl;

import com.github.pagehelper.PageInfo;
import com.srj.web.sys.mapper.SysUserMapper;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    public SysUserMapper sysUserMapper;

    @Override
    public SysUser CheckUser(String loginName) {
        SysUser sysuser = sysUserMapper.CheckSysUser(loginName);
        return sysuser;
    }
    //校验密码
    @Override
    public boolean CheckPassword(String loginPwd, String loginName) {
        return false;
    }
    //显示用户列表
    @Override
    public PageInfo<SysUser> findPageInfo(Map<String, Object> params) {
        return null;
    }
    //增加用户
    @Override
    public int addUser(SysUser user) {
        return 0;
    }
    //根据id查找用户
    @Override
    public SysUser getUserById(Long id) {
        return null;
    }
    //修改用户
    @Override
    public int editUser(SysUser record) {
        return 0;
    }
}

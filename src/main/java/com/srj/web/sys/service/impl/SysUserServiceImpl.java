package com.srj.web.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.base.PasswordEncoder;
import com.srj.common.constant.Constant;
import com.srj.web.sys.mapper.SysUserMapper;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysUserService;
import com.srj.web.tools.SysUserTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser CheckUser(String loginName) {
        SysUser sysuser = sysUserMapper.CheckSysUser(loginName);
        return sysuser;
    }
    //校验密码
    @Override
    public boolean CheckPassword(SysUser user,String loginPwd) {
        boolean b = false;
        String md5Pwd = PasswordEncoder.Encoding(loginPwd,user.getUsername());
        if(user.getPassword().equals(md5Pwd)){
            b = true;
        }
        return b;
    }
    //显示用户列表
    @Override
    public PageInfo<SysUser> findPageInfo(Map<String, Object> params) {
        PageHelper.startPage(params);
        List<SysUser> list = sysUserMapper.findPageInfo(params);
        //解密手机号
        list = SysUserTool.DecryptPhoneList(list);
        return new PageInfo<SysUser>(list);
    }
    //增加用户
    @Override
    public int addUser(SysUser user,String roleId) {
        user.setDelFlag(Constant.DEL_FLAG_NORMAL);
        //处理密码
        String password = user.getPassword();
        String md5Pwd = PasswordEncoder.Encoding(password,user.getUsername());
        user.setPassword(md5Pwd);
        //加密手机号
        user = SysUserTool.EncryptPhone(user);
        sysUserMapper.insert(user);
        //增加角色
        return sysUserMapper.insertUserRole(user.getId(),roleId);
    }
    //根据id查找用户
    @Override
    public SysUser getUserById(Long id) {
        SysUser user = sysUserMapper.getUserById(id);
        user = SysUserTool.DecryptPhone(user);
        return user;
    }
    //修改用户
    @Override
    public int editUser(SysUser record) {
        record = SysUserTool.EncryptPhone(record);
        int count = sysUserMapper.updateRecord(record);
        //删除用户-角色
        sysUserMapper.deleteUserRole(record.getId());
        //增加角色
        sysUserMapper.insertUserRole(record.getId(),record.getRoleId());
        return count;
    }

    //查全部，并把手机号加密存回去
    @Override
    public List<SysUser> changeAll() {
        List<SysUser> list = sysUserMapper.selectAll();
        list = SysUserTool.DecryptPhoneList(list);
        for(SysUser item : list){
            item = SysUserTool.EncryptPhone(item);
            sysUserMapper.updateRecord(item);
        }

        return null;
    }

}

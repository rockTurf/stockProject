package com.srj.web.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.common.utils.TreeUtils;
import com.srj.web.sys.mapper.SysResourceMapper;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    public SysResourceMapper sysResourceMapper;

    /**
     * 根据用户id得到用户持有的菜单资源
     * @return
     */
    public List<SysResource> findUserMenuByUserId(SysUser sysUser) {
        List<SysResource> reslist = new ArrayList<SysResource>();
        if (sysUser.isAdmin()) {
            Map<String,Object> params = new HashMap<>();
            params.put("type",Constant.RESOURCE_TYPE_MENU);
            reslist = sysResourceMapper.getAllResource(params);
        }else{
            reslist = sysResourceMapper.findUserResourceByUserId(Constant.RESOURCE_TYPE_MENU,sysUser.getId());
        }
        return reslist;
    }

    /**
     * 根据用户id得到用户持有的按钮资源
     * @return
     */
    public List<SysResource> findUserButtonByUserId(SysUser sysUser) {
        List<SysResource> reslist = new ArrayList<SysResource>();
        if (sysUser.isAdmin()) {
            Map<String,Object> params = new HashMap<>();
            params.put("type",Constant.RESOURCE_TYPE_MENU);
            reslist = sysResourceMapper.getAllResource(params);
        }else{
            reslist = sysResourceMapper.findUserResourceByUserId(Constant.RESOURCE_TYPE_BUTTON,sysUser.getId());
        }
        return reslist;
    }

    /**
     * 菜单管理分页显示筛选查询
     * @param params {"name":"菜单名字","id":"菜单id"}
     * @return
     */
    public PageInfo<SysResource> findPageInfo(Map<String, Object> params) {
        PageHelper.startPage(params);
        List<SysResource> list = sysResourceMapper.findPageInfo(params);
        return new PageInfo<SysResource>(list);
    }


    /**
     * 获取全部资源list形式
     * @return
     */
    public List<SysResource> getAllResourcesList() {
        Map<String,Object> params = new HashMap<>();
        List<SysResource> reslist = sysResourceMapper.getAllResource(params);
        return reslist;
    }
    /**
     * 获取菜单树
     */
    public List<SysResource> getMenuTree(){
        List<SysResource> list = TreeUtils.toTreeNodeList(getAllResourcesList());
        return list;
    }

    /**
     * 新增菜单栏
     * */
    public Integer saveRecord(SysResource record) {
        return sysResourceMapper.insertSelective(record);
    }
}

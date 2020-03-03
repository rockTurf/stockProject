package com.srj.web.sys.service.impl;

import com.github.pagehelper.PageInfo;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Override
    public List<SysResource> findUserMenuByUserId(SysUser sysUser) {
        return null;
    }

    @Override
    public List<SysResource> findUserButtonByUserId(SysUser sysUser) {
        return null;
    }

    @Override
    public PageInfo<SysResource> findPageInfo(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<SysResource> getAllResourcesList() {
        return null;
    }

    @Override
    public List<SysResource> getMenuTree() {
        return null;
    }

    @Override
    public Integer saveRecord(SysResource record) {
        return null;
    }
}

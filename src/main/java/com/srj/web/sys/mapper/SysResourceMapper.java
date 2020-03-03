package com.srj.web.sys.mapper;

import com.srj.web.sys.model.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysResourceMapper extends com.github.abel533.mapper.Mapper<SysResource>{

    List<SysResource> findPageInfo(Map<String, Object> params);

    //根据userId获得持有的权限
    List<SysResource> getAllResource(Map<String, Object> params);

    List<SysResource> findUserResourceByUserId(Map<String, Object> params);
}

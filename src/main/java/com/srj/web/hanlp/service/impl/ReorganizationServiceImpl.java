package com.srj.web.hanlp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.web.hanlp.mapper.ReorganizationMapper;
import com.srj.web.hanlp.model.Reorganization;
import com.srj.web.hanlp.service.ReorganizationService;
import com.srj.web.sys.model.SysFile;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysFileService;
import com.srj.web.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ReorganizationServiceImpl implements ReorganizationService {

    @Autowired
    ReorganizationMapper mapper;

    @Override
    public List<Reorganization> selectAll() {
        return mapper.selectAll();
    }

    //修改新闻-重整对应关系
    @Override
    public int editNewsReorg(Map<String, Object> params) {
        mapper.deleteNewsReorg(params);
        return mapper.editNewsReorg(params);
    }
    //根据新闻id找出重整步骤
    @Override
    public Long selectByNewsId(Long id) {
        return mapper.selectNewsReorgById(id);
    }
}

package com.srj.web.hanlp.controller;

import com.srj.web.hanlp.service.ReorganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@Transactional
@RequestMapping("reorganization")
public class ReorganizationController {

    @Autowired
    ReorganizationService service;

    /**
     * 修改
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/edit")
    public @ResponseBody Integer roleEdit(@RequestParam Map<String, Object> params){
        int count = service.editNewsReorg(params);
        return count;
    }
}

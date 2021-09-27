package com.srj.web.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.srj.common.constant.Constant;
import com.srj.web.datacenter.model.Keyword;
import com.srj.web.datacenter.model.News;
import com.srj.web.datacenter.service.KeywordService;
import com.srj.web.datacenter.service.NewsService;
import com.srj.web.util.DateUtils;
import com.srj.web.util.SpiderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Lazy(false)
@RequestMapping("quartz")
public class QuartzController {

      @Autowired
      NewsService newsService;
      @Autowired
      KeywordService keywordService;

      //天行新闻接口
      //@RequestMapping(value = "/news")
      @Scheduled(cron = "0 0 */1 * * ?")
      @ResponseBody
      public void getCsStockNews(){
            List<News> list = SpiderUtils.getTianNews();
            newsService.insertList(list);

      }

      //新闻接口转入旧表30 2 0 * * ? *
      @ResponseBody
      @Scheduled(cron = "30 3 0 * * ?")
      //@RequestMapping(value = "/clear")
      public void clearNewsToOld(){
            newsService.clearNewsToOld();
      }


      
}

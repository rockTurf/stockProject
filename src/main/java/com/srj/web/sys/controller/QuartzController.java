package com.srj.web.sys.controller;

import com.srj.web.datacenter.model.News;
import com.srj.web.datacenter.service.KeywordService;
import com.srj.web.datacenter.service.NewsService;
import com.srj.web.util.SpiderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
      @ResponseBody
      @Scheduled(cron = "20 3 * * * ?")
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

      //搜索新闻表，将包含关键词的新闻存入中间表



      
}

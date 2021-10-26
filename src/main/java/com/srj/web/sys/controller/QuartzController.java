package com.srj.web.sys.controller;

import com.srj.web.datacenter.model.Keyword;
import com.srj.web.datacenter.model.News;
import com.srj.web.datacenter.service.KeywordService;
import com.srj.web.datacenter.service.NewsService;
import com.srj.web.util.data.JisuData;
import com.srj.web.util.data.TianXingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
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
        public void getCsStockNews() {
            List<News> list = TianXingData.getTianNews();
            newsService.insertList(list);
            clearUnusualNews(list);

        }

        //急速新闻接口
        //@RequestMapping(value = "/news")
        @ResponseBody
        @Scheduled(cron = "20 4 * * * ?")
        public void getJisuApiNews() throws UnsupportedEncodingException {
            List<News> list = JisuData.getJisuNews();
            newsService.insertList(list);
            //清洗异常数据
            clearUnusualNews(list);

        }


        //新闻接口转入旧表30 2 0 * * ? *
        @ResponseBody
        @Scheduled(cron = "30 3 0 * * ?")
        //@RequestMapping(value = "/clear")
        public void clearNewsToOld() {
            newsService.clearNewsToOld();
        }

        //搜索新闻表，将包含关键词的新闻存入中间表
        @ResponseBody
        //@Scheduled(cron = "30 3 0 * * ?")
        @RequestMapping(value = "/clear")
        public void updateKeyword() {
            //取出所有新闻
            List<News> newsList = newsService.getAll();
            //清洗异常数据
            clearUnusualNews(newsList);
        }


        //私有方法，清洗异常数据
        private void clearUnusualNews(List<News> list) {
            //取出所有关键词
            List<Keyword> keywordList = keywordService.getAllUnusualKeyword();
            //清洗异常
            for (News news : list) {
                //循环关词
                for (Keyword key : keywordList) {
                    newsService.getInNewsKeyword(news, key);
                }

            }
        }


}

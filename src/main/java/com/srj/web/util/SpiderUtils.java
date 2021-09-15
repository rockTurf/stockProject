package com.srj.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srj.common.utils.HttpRequestUtil;
import com.srj.web.datacenter.model.News;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class SpiderUtils {


    protected static final Logger logger = LoggerFactory.getLogger(SpiderUtils.class);

    //天行app
    private static String TIAN_API = "http://api.tianapi.com/caijing/index";
    private static String TIAN_KEY = "f1bb52f045df3bd96468932ca5353305";
    private static Integer TIAN_NUMBER = 50;


    /**
     * 获取中国证券网的股票新闻JSON
     *
     */
    public static List<News> getTianNews() {
        List<News> list = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("key",TIAN_KEY);
        map.put("num",TIAN_NUMBER);
        String str = HttpRequestUtil.sendPost(TIAN_API,map,"UTF-8");
        //解析JSON
        JSONObject json = JSON.parseObject(str);
        if(200==json.getInteger("code")){
            JSONArray jsonArray = json.getJSONArray("newslist");
            for(int i=0;i<jsonArray.size();i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String news_time = obj.getString("ctime");
                String title = obj.getString("title");
                String source = obj.getString("source");
                String picUrl = obj.getString("picUrl");
                String address = obj.getString("url");
                String create_time = DateUtils.getDateTime();

                News item = new News();
                item.setSource(source);
                item.setTitle(title);
                item.setAddress(address);
                item.setNewsTime(news_time);
                item.setPicUrl(picUrl);
                item.setCreateTime(create_time);
                list.add(item);
            }

        }
        return list;
    }
}

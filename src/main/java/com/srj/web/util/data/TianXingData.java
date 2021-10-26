package com.srj.web.util.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srj.common.utils.HttpRequestUtil;
import com.srj.web.datacenter.model.News;
import com.srj.web.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class TianXingData {


    protected static final Logger logger = LoggerFactory.getLogger(TianXingData.class);

    //天行app
    private static String TIAN_API = "http://api.tianapi.com/caijing/index";
    private static String TIAN_KEY = "f1bb52f045df3bd96468932ca5353305";
    private static Integer TIAN_NUMBER = 50;


    /**
     * 获取天行
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

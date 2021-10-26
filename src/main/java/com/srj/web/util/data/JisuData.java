package com.srj.web.util.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srj.common.utils.HttpRequestUtil;
import com.srj.web.datacenter.model.News;
import com.srj.web.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JisuData {


    protected static final Logger logger = LoggerFactory.getLogger(JisuData.class);

    //急速
    public static final String APPKEY = "31f26a8211f8a589";// 你的appkey
    public static final String URL = "https://api.jisuapi.com/news/get";
    public static final String channel = "财经";// utf8  新闻频道(头条,财经,体育,娱乐,军事,教育,科技,NBA,股票,星座,女性,健康,育儿)
    public static final int num = 30;// 数量 默认10，最大40
    public static final int start = 0;//起始


    /**
     * 获取天行
     *
     */
    public static List<News> getJisuNews() throws UnsupportedEncodingException {
        List<News> newsList = new ArrayList<>();

        String result = null;
        String url = URL + "?channel=" + URLEncoder.encode(channel, "utf-8") + "&start="+ start + "&num=" + num + "&appkey=" + APPKEY;

        try {
            result = HttpRequestUtil.sendGet(url, null);
            JSONObject json = JSONObject.parseObject(result);
            if (json.getInteger("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONObject resultarr = (JSONObject) json.get("result");
                String channel = resultarr.getString("channel");
                String num = resultarr.getString("num");
                System.out.println(channel + " " + num);
                JSONArray list = resultarr.getJSONArray("list");
                for (int i = 0; i < list.size(); i++) {
                    JSONObject obj = (JSONObject) list.getJSONObject(i);
                    String title = obj.getString("title");
                    String time = obj.getString("time");
                    String src = obj.getString("src");
                    String category = obj.getString("category");
                    String pic = obj.getString("pic");
                    String content = obj.getString("content");
                    String address = obj.getString("url");
                    String weburl = obj.getString("weburl");

                    News item = new News();
                    item.setSource(src);
                    item.setTitle(title);
                    item.setAddress(address);
                    item.setNewsTime(time);
                    item.setCreateTime(DateUtils.getDateTime());
                    item.setContent(content);
                    item.setPicUrl(pic);
                    newsList.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }
}

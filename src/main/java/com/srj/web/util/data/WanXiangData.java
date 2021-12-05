package com.srj.web.util.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srj.common.utils.HttpRequestUtil;
import com.srj.web.datacenter.model.News;
import com.srj.web.util.DateUtils;
import com.srj.web.util.TxtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WanXiangData {


    protected static final Logger logger = LoggerFactory.getLogger(WanXiangData.class);

    //急速
    public static final String APPKEY = "1e7a40bb0fc8156efddf83afd2789bbf";// 你的appkey
    public static final String channel = "财经";// utf8  新闻频道(头条,财经,体育,娱乐,军事,教育,科技,NBA,股票,星座,女性,健康,育儿)
    public static final int num = 40;// 数量 默认10，最大40
    public static final int start = 0;//起始


    /**
     * 获取天行
     *
     */
    public static List<News> getNews() throws UnsupportedEncodingException {

        String URL = "https://way.jd.com/jisuapi/get?channel="+channel+"&num="+num+"&start=0&appkey="+APPKEY;

        List<News> newsList = new ArrayList<>();

        String result = null;
        Map<String,Object> map = new HashMap<>();
        map.put("channel",URLEncoder.encode(channel, "utf-8"));
        map.put("start",start);
        map.put("num",num);
        map.put("appkey",APPKEY);
        try {
            result = HttpRequestUtil.sendPost(URL, map,"utf-8");
            //logger.info(result);
            //System.out.println(result);
            JSONObject json = JSON.parseObject(result);
            if (!json.getString("code").equals("10000")) {
                System.out.println(json.getString("msg"));
            } else {
                JSONObject reObj = json.getJSONObject("result");
                JSONObject resultarr = reObj.getJSONObject("result");
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

                    //清洗一下内容字段
                    content = TxtUtil.delHtmlTags(content);

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

package com.srj.common.utils;

import com.srj.web.util.DateUtils;
import org.junit.Test;

import java.util.*;

public class OtherUtils {

    //Set排序
    public static Set<String> sortSet(Set<String> set){
        Set<String> sortSet = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);//降序排列
            }
        });
        sortSet.addAll(set);
        return sortSet;
    }

    /**
     *
     * @Title : filterNumber
     * @Type : FilterStr
     * @date : 2014年3月12日 下午7:23:03
     * @Description : 过滤出数字
     * @return
     */
    public static String filterNumber(String number)
    {
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }
    /**
     *
     * @Title : getDateByTitle
     * @Type : FilterStr
     * @date : 2014年3月12日 下午7:23:03
     * @Description : 从标题获得月份和日期
     * @return
     */
    public static String getDateByTitle(String title){
        int MONTH = 0;
        int DAY = 0;
        if(title.contains("月")){
            //找到第一个“月”出现的位置
            int index = title.indexOf("月");
            String month_ = "";//取出“月”前面的一个或两个字
            if(index>=2){//如果在第二个字之后
                month_ = title.substring(index-2,index);
            }else if(index==1){//如果月是第二个字，取出第一个字
                month_ = title.substring(0,1);
            }

            if(month_.length()>0){//有值
                String num = OtherUtils.filterNumber(month_);
                MONTH = Integer.parseInt(num);
                System.out.println("月："+MONTH);
            }
        }

        if(title.contains("日")){
            //找到第一个“日”出现的位置
            int index = title.indexOf("日");
            String day_ = "";//取出“日”前面的一个或两个字
            if(index>=2){//如果在第二个字之后
                day_ = title.substring(index-2,index);
            }else if(index==1){//如果日是第二个字，取出第一个字
                day_ = title.substring(0,1);
            }

            if(day_.length()>0){//有值
                String num = OtherUtils.filterNumber(day_);
                DAY = Integer.parseInt(num);
                System.out.println("日："+DAY);
            }
        }
        if(MONTH!=0&&DAY!=0){
            String tempDateTime = DateUtils.getYear()+"-"+MONTH+"-"+DAY;
            //为了避免12-31号的情况，应把取到的日期和今天时间对比，如果超过，则后退一年
            return tempDateTime;
        }
        return null;
    }

    /**
     *
     * @Title : getDateTime
     * @date : 2014年3月12日 下午7:23:03
     * @Description : 根据日期和时间拼接整体的时间字符串
     * @return
     */
    public static String getDateTime(String date, String time) {
        String datetime = date+" "+time;
        Date d = DateUtils.parseDate(datetime);
        String str = DateUtils.formatDateTime(d);
        return str;
    }

    /**
     *
     * @date : 2021年10月26日
     * @Description : 关键词标红
     * @return
     */
    public static String setRed(String title, List<String> strList) {
        if(strList.size()<=0){
            return title;
        }
        for(String str : strList){
            if(title.indexOf(str)!=-1){
                title = title.replaceAll(str,"<font color='red'>"+str+"</font>");
                return title;
            }
        }
        return title;
    }
}

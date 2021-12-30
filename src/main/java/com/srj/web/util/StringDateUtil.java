package com.srj.web.util;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 时间字符串的匹配
 * */
public class StringDateUtil {

    /**
     *
     * @Description: 从字符串中截取出正确的时间
     * @param stringTime
     * @return:
     * @throws
     */
    public static Date cutDate(String stringTime) {
        String regs[] = { "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}时\\d{2}分",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}时\\d{2}分",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}时",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{1}时",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}时",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}时",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}时", "\\d{4}年\\d{2}月\\d{2}日",
                "\\d{4}年\\d{2}月\\d{1}日", "\\d{4}年\\d{1}月\\d{2}日",
                "\\d{4}年\\d{1}月\\d{1}日",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}:\\d{1}:\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}:\\d{1}:\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{1}:\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}:\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{1}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}", "\\d{4}-\\d{2}-\\d{2}",
                "\\d{4}-\\d{2}-\\d{1}", "\\d{4}-\\d{1}-\\d{2}",
                "\\d{4}-\\d{1}-\\d{1}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{1}-\\d{1}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}时",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}时",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{2}时",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{1}时", "\\d{4}.\\d{2}.\\d{2}",
                "\\d{4}.\\d{2}.\\d{1}", "\\d{4}.\\d{1}.\\d{2}",
                "\\d{4}.\\d{1}.\\d{1}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{1}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{1}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{1}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}时",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}时",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}时",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}时",
                "\\d{4}/\\d{2}/\\d{2}",
                "\\d{4}/\\d{2}/\\d{1}",
                "\\d{4}/\\d{1}/\\d{2}",
                "\\d{4}/\\d{1}/\\d{1}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}",
                "\\d{2}月\\d{2}日\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}月\\d{2}日\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}月\\d{2}日\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}月\\d{2}日\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{1}月\\d{2}日\\s\\d{1}时\\d{2}分",
                "\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{2}月\\d{2}日\\d{2}时\\d{2}分",
                "\\d{2}月\\d{2}日\\s\\d{2}时",
                "\\d{2}月\\d{2}日\\s\\d{1}时",
                "\\d{1}月\\d{2}日\\s\\d{2}时",
                "\\d{1}月\\d{2}日\\s\\d{1}时",
                "\\d{2}月\\d{2}日\\d{2}时", "\\d{4}年\\d{2}月\\d{2}日",
                "\\d{2}月\\d{1}日", "\\d{4}年\\d{1}月\\d{2}日",
                "\\d{1}月\\d{1}日",
                "\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{2}:\\d{1}:\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{2}:\\d{1}:\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{1}:\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{2}:\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{1}:\\d{2}",
                "\\d{2}月\\d{2}日\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{1}",
                "\\d{1}月\\d{2}日\\s\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{1}",
                "\\d{2}月\\d{2}日\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{1}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{1}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{2}", "\\d{4}-\\d{2}-\\d{2}",
                "\\d{2}-\\d{1}", "\\d{4}-\\d{1}-\\d{2}",
                "\\d{1}-\\d{1}",
                "\\d{2}-\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}-\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}-\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{1}-\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}-\\d{1}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{2}-\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{2}-\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{1}-\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{1}-\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{2}-\\d{2}\\s\\d{2}时",
                "\\d{2}-\\d{2}\\s\\d{1}时",
                "\\d{1}-\\d{2}\\s\\d{2}时",
                "\\d{1}-\\d{2}\\s\\d{1}时", "\\d{4}.\\d{2}.\\d{2}",
                "\\d{2}.\\d{1}", "\\d{4}.\\d{1}.\\d{2}",
                "\\d{1}.\\d{1}",
                "\\d{2}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{1}.\\d{1}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{1}",
                "\\d{1}.\\d{2}\\s\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{1}",
                "\\d{2}/\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}/\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}/\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{1}/\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{2}/\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{2}/\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{1}/\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{1}/\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{2}/\\d{2}\\s\\d{2}时",
                "\\d{2}/\\d{2}\\s\\d{1}时",
                "\\d{1}/\\d{2}\\s\\d{2}时",
                "\\d{1}/\\d{2}\\s\\d{1}时",
                "\\d{2}/\\d{2}",
                "\\d{2}/\\d{1}",
                "\\d{1}/\\d{2}",
                "\\d{1}/\\d{1}",
                "\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{1}",
                "\\d{1}/\\d{2}\\s\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{1}",
        };
        String str = "";
        Date date = null;
        for (String reg : regs) {
            String temp = match(reg, stringTime);
            if (temp.length() > str.length()) {
                str = temp;
                if (!"".equals(str)) {
                    date = formatDate(str);
                }
            }
        }
        return date;
    }
    /**
     *
     * @Description: 把String格式的时间转化为date
     * @param stringTime
     * @return:
     * @throws
     */
    public static Date formatDate(String stringTime) {
        Date date = null;
        if (StringUtils.isNotBlank(stringTime)) {
            String[] pattern = new String[] { "yyyy年MM月dd日HH时mm分ss秒",
                    "yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日HH时mm分",
                    "yyyy年MM月dd日 HH时mm分", "yyyy年MM月dd日 HH时", "yyyy年MM月dd日HH时",
                    "yyyy年MM月dd日", "yyyy年MM月dd日HH:mm:ss",
                    "yyyy年MM月dd日 HH:mm:ss", "yyyy年MM月dd日HH:mm",
                    "yyyy年MM月dd日 HH:mm", "yyyy年MM月dd日 HH", "yyyy年MM月dd日HH",
                    "yyyy-MM-dd HH时mm分ss秒", "yyyy-MM-dd HH时mm分",
                    "yyyy-MM-dd HH时", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss",
                    "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH",
                    "yyyy/MM/dd HH时mm分ss秒", "yyyy/MM/dd HH时mm分",
                    "yyyy/MM/dd HH时", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
                    "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH", "yyyy.MM.dd HH:mm:ss",
                    "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH", "yyyy.MM.dd",
                    "yyyyMMdd", };
            try {
                date = DateUtils.parseDate(stringTime, pattern);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
    public static String match(String reg, String stringTime) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(stringTime);
        String s = "";
        if (m.find()) {
            s += m.group();
        }
        return s;
    }
    /**
     * 格式化
     * @Title: DateToString
     * @Description:
     * @param time
     * @return:
     * @throws
     */
    public static String DateToString(Date time) {
        if(time==null){
            return "";
        }
        String newDate = DateFormatUtils.format(time,"yyyy-MM-dd");
        return newDate;
    }

    public static void main(String[] args) {
        String stringTime = "本次资本公积金转增股本后，公司A股股票除权除息参考价格为公司A股股票2021-12-03收盘价格。";
        Date date = cutDate(stringTime);
        System.out.println(date);
        System.out.println(DateToString(date));
    }
}

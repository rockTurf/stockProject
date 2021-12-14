package com.srj.web.tools.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.web.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Hanlp的语句处理工具 日期时间部分
 * */
public class HanlpDateTool {

    public static final String[] YEAR_NATURE = {"年"};
    public static final String[] MONTH_NATURE = {"月"};
    public static final String[] DAY_NATURE = {"日","号"};

    /**
     * 处理日期工具
     * */
    public static String handleDateTime(String text){
        text = text.replaceAll(" ","");
        text = HanlpCleanTool.clearLine(text);
        //具体详见公司于 2020年3月28日披露的《关于债权人申请公司重整的提示性公告》（公告编号：2020-032）
        List<Term> list = HanLP.segment(text);
        //先去除掉所有的空元素
        list = HanlpCleanTool.cleanNullValue(list);
        //循环，先找到【年】的位置
        for(int i=0;i<list.size();i++){
            Term t = list.get(i);
            if(isPortContain(YEAR_NATURE,t.word)){
                //去找它的上一个，是不是m（数字）
                Term lastT = list.get(i-1);
                if("m".equals(lastT.nature.toString())){
                    //判断该数字是否为年份单位
                    boolean isYear = judgeYear(lastT.word);
                    if(isYear==true){
                        //xxxx年，我们继续往下找，看看是否后面跟了x月
                        if(i>=list.size()-2){
                            break;
                        }
                        Term monthM = list.get(i+1);
                        Term monthN = list.get(i+2);
                        //如果下一位是数字，再下一位是“月”这个字，就继续判断
                        if("m".equals(monthM.nature.toString())&& isPortContain(MONTH_NATURE,monthN.word)){
                            //如果跟的是月份，判断是否为月份单位
                            boolean isMonth = judgeMonth(monthM.word);
                            if(isMonth==true){
                                //再往下就看看有没有“日”单位了
                                if(i>=list.size()-4){
                                    break;
                                }
                                Term dayM = list.get(i+3);
                                Term dayN = list.get(i+4);
                                if("m".equals(dayM.nature.toString())&& isPortContain(DAY_NATURE,dayN.word)) {
                                    //如果连日都有，那就是完整的x年x月x日的日期格式(暂时不考虑时分秒)
                                    String dateStr = lastT.word+"年"+monthM.word+"月"+dayM.word+"日";
                                    //将日期格式化后替掉原来的文字
                                    String replaceStr = lastT.word+"/"+monthM.word+"/"+dayM.word;
                                    text = text.replaceAll(dateStr,replaceStr);
                                    CustomDictionary.add(replaceStr,"t 1");
                                }else{
                                    //没有“日”，那就是 【x年x月】的日期格式
                                    String dateStr = lastT.word+"年"+monthM.word+"月";
                                    //将日期格式化后替掉原来的文字
                                    String replaceStr = lastT.word+"/"+monthM.word;
                                    text = text.replaceAll(dateStr,replaceStr);
                                    CustomDictionary.add(replaceStr,"t 1");
                                }
                            }
                        }
                    }
                }
            }
        }
        return text;
    }

    /**
     * 判断字符串是否为年份
     * */
    private static boolean judgeYear(String word) {
        //如果是两位数字或者四位数字，我们就判断它为年份
        if(StringUtils.isNumeric(word)){
            if(word.length()==2||word.length()==4){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否为月份
     * */
    private static boolean judgeMonth(String word) {
        //如果是两位数字或者四位数字，我们就判断它为年份
        if(StringUtils.isNumeric(word)){
            if(word.length()==1){//一位直接过
                return true;
            }else if(word.length()==2){//两位就分开判断
                //判断其第一位是0直接过
                String f = word.substring(0,1);
                if("0".equals(f)){
                    return true;
                }
                //第一位是1的话，转化数字，小于等于12可以过
                if("1".equals(f)){
                    Integer num = Integer.parseInt(word);
                    if(num<=12){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 词语 只要部分匹配数组中的元素就算
     * */
    public static boolean isPortContain(String[] array,String word){
        for(String str : array){
            //str 是 【日】【号】 word是【日前】 这种算匹配
            if(word.indexOf(str)==0){
                return true;
            }
        }
        return false;
    }
}

package com.srj.web.tools.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Hanlp的语句处理工具 日期时间部分
 * */
public class HanlpDateTool {

    /**
     * 处理日期工具
     * */
    public static String handleDateTime(String text){
        //具体详见公司于 2020年3月28日披露的《关于债权人申请公司重整的提示性公告》（公告编号：2020-032）
        List<Term> list = HanLP.segment(text);
        //先去除掉所有的空元素
        list = HanlpCleanTool.cleanNullValue(list);
        //循环，先找到【年】的位置
        for(int i=0;i<list.size();i++){
            Term t = list.get(i);
            if("年".equals(t.word)){
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
                        if("m".equals(monthM.nature.toString())){
                            if("月".equals(monthN.word)){
                                //如果跟的是月份，判断是否为月份单位
                                boolean isMonth = judgeMonth(monthM.word);
                                if(isMonth==true){
                                    System.out.println("true="+monthM.word);
                                }else{
                                    System.out.println("false="+monthM.word);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
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
}

package com.srj.web.tools.hanlp;

import com.hankcs.hanlp.seg.common.Term;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * HANLP 清洗词库的工具包
 * */
public class HanlpCleanTool {

    /**
     * 清洗掉空值的元素
     * */
    public static List<Term> cleanNullValue(List<Term> list){
        Iterator<Term> iter = list.iterator();
        while (iter.hasNext()) {
            Term item = iter.next();
            //首先筛出natrue = w的值
            if ("w".equals(item.nature.toString())){
                //判断是否为空
                String str = item.word;
                str = str.replace(" ", "");
                if(StringUtils.isEmpty(str)){
                    //如果为空，去除这个元素
                    iter.remove();
                }
            }
        }
        return list;
    }

    /**
     * 补正文档解析后的情况
     * */
    public static String clearAfterPdf(String text){
        if(StringUtils.isEmpty(text)){
            return text;
        }
        text = text.replaceAll(" ","");
        //如果最后一位是【/】，给补一位【>】
        String lastStr = text.substring(text.length()-1);
        if("/".equals(lastStr)){
            text = text + ">";
        }
        return text;
    }

    /**
     * 处理换行符</br>
     * */
    public static String clearLine(String text){
        if(StringUtils.isEmpty(text)){
            return text;
        }
        //这个是返回值
        StringBuffer buffer = new StringBuffer();
        //这个是原始文件每个空格行输出的结果
        String[] array = text.split("<br/>");
        //清理行
        array = HanlpLineTool.cleanLineMethod(array);
        //获取一般行宽
        int lineCount = getLineCount(array);
        //分解这些句子，看看哪些是换行的
        for(int i=0;i<array.length;i++){
            String str = array[i];
            buffer.append(str);
            //判断是否需要换行
            if(checkChangeLine(str,lineCount)==true){
                buffer.append("\n");
            }
        }
        return buffer.toString();
    }
    /**
     * 判断单行是否需要换行
     * */
    private static boolean checkChangeLine(String str,Integer lineCount) {
        //关于xxx公告
        if(str.length()>4){
            String start = str.substring(0,2);
            String end = str.substring(str.length()-2,str.length());
            if("关于".equals(start)&&"公告".equals(end)){
                return true;
            }
        }
        //最后一位为句号
        String endStrOne = str.substring(str.length()-1,str.length());
        if("。".equals(endStrOne)){
            return true;
        }
        //句长不超过行宽个字且最后一位不是句号
        //System.out.println(str+"-----"+"长度="+str.length());
        if(str.length()<=lineCount){
           return true;
        }
        return false;
    }

    /**
     * 根据句子，计算出一行的长度
     * */
    private static int getLineCount(String[] array) {
        //根据各个句子长度，计算出一行是多少个字
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0;i<array.length;i++){
            set.add(array[i].length());
        }

        //倒序 从大到小排列
        TreeSet<Integer> intsReverse = (TreeSet<Integer>)set.descendingSet();
        //转List
        List <Integer> list = new ArrayList<Integer>(intsReverse);
        //取前四个，算平均值
        int top1 = list.get(0);
        int top2 = list.get(1);
        int top3 = list.get(2);
        int top4 = list.get(3);
        int ave = top1/2;
        //System.out.println("行宽="+ave);
        return ave;
    }
}

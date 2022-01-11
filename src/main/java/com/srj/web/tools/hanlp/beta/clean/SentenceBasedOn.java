package com.srj.web.tools.hanlp.beta.clean;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.web.tools.hanlp.HanlpBaseTool;
import com.srj.web.tools.hanlp.HanlpConstant;
import com.srj.web.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 清除【根据，依据】等关键词的句子
 * 例如：根据《中华人民共和国企业破产法》的相关规定
 * */
public class SentenceBasedOn {

    public static String method(String text) {
        //按句号分隔
        String[] longSentenceArray = text.split("。");
        //再循环句号中的逗号，找到无用关键词
        first:for (String sentence : longSentenceArray) {
            String[] shortSentenceArray = sentence.split("，");
            second:for (String shortSentence : shortSentenceArray) {
                if(StringUtil.isIncludeArray(shortSentence, HanlpConstant.USELESS_WORD_BASE_ON)){
                    //分词看看里面有没有名词
                    List<Term> list = HanLP.segment(shortSentence);
                    boolean b = HanlpBaseTool.isTermListInlude(list,"v");
                    //如果没有名词，直接清除
                    if(b==false) {
                        //清除方法
                        text = cleanMethod(text,shortSentence);
                        break first;
                    }
                }
            }
        }
        text = text.replaceAll("，。", "。");
        text = text.replaceAll("。。", "。");
        return text;
    }

    /**
     * 清除方法
     * 原文：1、根据《中华人民共和国企业破产法》的相关规定
     * 清除后：1、
     * 原文：根据本所《股票上市规则》第16.1条规定
     * 全部清除
     * */
    private static String cleanMethod(String text,String shortSentence) {
        List<Term> list = HanLP.segment(shortSentence);
        //看是否包含数字
        boolean b = HanlpBaseTool.isTermListInlude(list,"m");
        if(b==false) {
            //数字都没有就直接清掉了
            text = text.replaceAll(shortSentence,"");
            return text;
        }
        //包含了数字的话,去掉序号1、（一）等
        //String shortSentenceSort = cleanShortSentenceSort(shortSentence);
        //去掉主语
        String shortSentenceN = cleanShortSentenceN(shortSentence);
        text = text.replaceAll(shortSentenceN+"，","");


        return text;
    }

    /**
     * 清洗 如果有序号，去掉序号
     * 原文：1、根据《中华人民共和国企业破产法》的相关规定
     * 返回：根据《中华人民共和国企业破产法》的相关规定
     * */
    private static String cleanShortSentenceSort(String text) {
        List<Term> list = HanLP.segment(text);
        //第一种序号形式：数字加顿号
        Term t1 = list.get(0);
        Term t2 = list.get(1);
        Term t3 = list.get(2);
        //1、
        if("m".equals(t1.nature.toString())&&"w".equals(t2.nature.toString())){
            String sort = t1.word+t2.word;
            text = text.replaceAll(sort,"");
            return text;
        }
        //（一）
        if("（".equals(t1.word)&&"m".equals(t2.nature.toString())&&"）".equals(t3.word)){
            String sort = t1.word+t2.word+t3.word;
            text = text.replaceAll(sort,"");
            return text;
        }
        return text;
    }

    /**
     * 清洗 如果有主语，去掉主语
     * 原文：深圳证券交易所将根据《深圳证券交易所创业板股票上市规则》第10.4.1条第七项的规定
     * 返回：根据《深圳证券交易所创业板股票上市规则》第10.4.1条第七项的规定
     * */
    private static String cleanShortSentenceN(String text) {
        for(String str:HanlpConstant.USELESS_WORD_BASE_ON){
            CustomDictionary.add(str,"base 1");
        }
        //这是主语和副词
        StringBuffer buffer = new StringBuffer();
        List<Term> list = HanLP.segment(text);
        for(Term t:list){
            if("base".equals(t.nature.toString())){
                break;
            }
            buffer.append(t.word);
        }
        String ntp = buffer.toString();
        text = text.replaceAll(ntp,"");
        return text;

    }

}

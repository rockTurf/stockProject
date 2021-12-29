package com.srj.web.tools.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * 数据+单位的清洗
 */
public class DataUnitClean {

    /**
     * 清洗数据
     */
    public static String cleanMethod(String text) {
        String[] array = text.split("\\r?\\n");
        for (String str : array) {
            String[] arrSentence = str.split("。");
            for (String sentence : arrSentence) {
                if(sentence.indexOf("董事会")!=-1&&sentence.indexOf("保证")!=-1){
                    List<Term> list = HanLP.segment(str);
                    System.out.println(sentence);
                    for(Term t:list){
                        System.out.print(t.word+"|"+t.nature+"  ");
                    }
                }
            }
        }
        return text;
    }
}
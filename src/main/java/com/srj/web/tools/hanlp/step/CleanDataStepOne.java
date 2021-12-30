package com.srj.web.tools.hanlp.step;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.web.tools.hanlp.HanlpConstant;
import com.srj.web.util.StringUtil;

import java.util.List;

/**
 * 清洗数据的步骤一
 * */
public class CleanDataStepOne {

    /**
     * 首先将【详见】等关键词后的句子统一清掉
     * */
    public static String cleanMethod(String text){
        //按句号分隔
        String[] longSentenceArray = text.split("。");
        //循环短句，找到无用关键词
        for(String sentence:longSentenceArray){
            String[] shortSentenceArray = sentence.split("，");
            for(String shortSentence : shortSentenceArray) {
                //如果有【无用关键词】，就删除
                if (StringUtil.isIncludeArray(shortSentence, HanlpConstant.USELESS_WORD)) {
                    //System.out.println(shortSentence);
                    text = text.replaceAll(shortSentence, "");
                }
            }
        }
        text = text.replaceAll("，。", "。");
        text = text.replaceAll("。。", "。");
        return text;
    }
}

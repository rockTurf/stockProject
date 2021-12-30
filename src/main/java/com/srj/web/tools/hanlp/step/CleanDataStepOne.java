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
    public static String cleanMethod(String text) {
        //按句号分隔
        String[] longSentenceArray = text.split("。");
        //循环句号，找到无用关键词
        for (String sentence : longSentenceArray) {
            //如果有【无用关键词】，就删除
            if (StringUtil.isIncludeArray(sentence, HanlpConstant.USELESS_WORD_LONG)) {
                //System.out.println(shortSentence);
                text = text.replaceAll(sentence, "");
            }
        }
        //再循环句号中的逗号，找到无用关键词
        for (String sentence : longSentenceArray) {
            String[] shortSentenceArray = sentence.split("，");
            for (String shortSentence : shortSentenceArray) {
                //如果有【无用关键词】，就删除
                if (StringUtil.isIncludeArray(shortSentence, HanlpConstant.USELESS_WORD_SHORT)) {
                    //System.out.println(shortSentence);
                    CustomDictionary.add(shortSentence, "del 1");
                    text = text.replaceAll(shortSentence, "");
                }
                if(shortSentence.indexOf("根据")!=-1||shortSentence.indexOf("依据")!=-1){
                    List<Term> list = HanLP.segment(shortSentence);
                    boolean b = false;
                    for(Term t:list){
                        //System.out.print(t.word+"|"+t.nature+"|");
                        if("v".equals(t.nature.toString())){
                            b = true;
                        }
                    }
                    if(b==false){
                        System.out.println(shortSentence);
                    }
                    //System.out.println("\n");
                }
            }
        }
        text = text.replaceAll("，。", "。");
        text = text.replaceAll("。。", "。");
        return text;
    }
}

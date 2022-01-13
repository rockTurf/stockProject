package com.srj.web.tools.hanlp.beta.clean;

import com.srj.web.tools.hanlp.HanlpConstant;
import com.srj.web.util.StringUtil;

/**
 * 清除【详见】等关键词的句子
 * 例如：详见公司于2019年5月18日在深圳证券交易所网站及指定信息披露媒体《证券时报》
 * 和巨潮资讯网：http://www.cninfo.com.cn披露的《关于公司股票被实施其他风险警示的公告》（
 * 公告编号：2019-079）。
 * */
public class SentenceIF {

    public static String method(String text) {
        //按句号分隔
        String[] longSentenceArray = text.split("。");
        //再循环句号中的逗号，找到无用关键词
        for (String sentence : longSentenceArray) {
            //如果有【无用关键词】，就删除
            if (StringUtil.isIncludeArray(sentence, HanlpConstant.USELESS_WORD_IF)) {
                System.out.println(sentence);
            }
           /* String[] shortSentenceArray = sentence.split("，");
            for (String shortSentence : shortSentenceArray) {

            }*/
        }
        text = text.replaceAll("，。", "。");
        text = text.replaceAll("。。", "。");
        return text;
    }
}

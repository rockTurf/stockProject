package com.srj.web.tools.hanlp.beta;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.web.tools.hanlp.HanlpConstant;
import com.srj.web.tools.hanlp.beta.clean.SentenceBasedOn;
import com.srj.web.tools.hanlp.beta.clean.SentenceDetail;
import com.srj.web.tools.hanlp.beta.clean.SentenceIF;
import com.srj.web.util.StringUtil;

import java.util.List;

/**
 * 清洗数据的步骤（整句按逗号分割，清部分）
 * */
public class CleanDataStep {

    //按行拆分
    public static String cleanSentenceMethod(String text){
        //将【详见】等关键词后的句子统一清掉
        String str = SentenceDetail.method(text);
        //清除【依据】【根据】等法律条文
        String str2 = SentenceBasedOn.method(str);
        //清除【如】【若】等不确定的词汇
        String str3 = SentenceIF.method(str2);

        String finalStr = str3;
        return finalStr;
    }


}

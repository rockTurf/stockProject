package com.srj.common.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.tokenizers.HanLPTokenizer;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.junit.Test;

public class TestClass {

    public static String ss = "恒泰地产回应破产重整：确实遇困难，尽一切办法恢复正常经营";

    @Test
    public void test1(){
        //CustomDictionary.add("恒大集团董事会","ntc");

        System.out.println(HanLP.segment(ss));
    }

}

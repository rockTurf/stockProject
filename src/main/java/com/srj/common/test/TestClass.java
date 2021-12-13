package com.srj.common.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.tokenizers.HanLPTokenizer;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.common.tools.Pdf2TextTool;
import org.junit.Test;

import java.util.List;

public class TestClass {

    @Test
    public void pdfTest(){
        /*String filePath = "F:\\work\\ST华讯 华讯方舟股份有限公司关于预重整债权申报的通知-20210903.pdf";
        String str = Pdf2TextTool.PDF2String(filePath);*/
       /* CustomDictionary.add("*ST华讯","nts 1");
        CustomDictionary.add("ST华讯","nts 1");*/
        String str = "证券代码：000687 证券简称：*ST华讯 公告编号：2021-077";
        List<Term> list = HanLP.segment(str);
        for(Term t: list){
            System.out.println(t.word+"   "+t.nature);
        }

    }
}

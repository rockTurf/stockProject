package com.srj.common.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.tokenizers.HanLPTokenizer;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.common.tools.Pdf2TextTool;
import com.srj.common.utils.FileUtil;
import com.srj.web.tools.hanlp.HanlpDateTool;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestClass {

    @Test
    public void pdfTest(){
        String filePath = "F:\\work\\pdf";
        List<String> pdfList = FileUtil.getFileList(filePath);
        for(String pdfFile:pdfList){
            String str = Pdf2TextTool.PDF2String(filePath+"/"+pdfFile);
            str = HanlpDateTool.handleDateTime(str);
            FileUtil.writeFile(str,filePath+"/"+pdfFile+".txt");
        }

       /* CustomDictionary.add("*ST华讯","nts 1");
        CustomDictionary.add("ST华讯","nts 1");*/
       //String str = "具体详见公司于 2020 年 3 月 28 日披露的《关于债权人申请公司重整的提示性公告》（公告编号：2020-032）";
        //CustomDictionary.add("2020年3月28日","t 10");
        /*List<Term> list = HanLP.segment(str);
        for(Term t: list){
            System.out.println(t.word+"   "+t.nature);
        }*/

        //List<Term> list = HanLP.segment(str);
        /*for(Term t: list){
            System.out.println(t.word+","+t.nature);
        }*/
    }
}

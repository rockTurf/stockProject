package com.srj.common.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.tokenizers.HanLPTokenizer;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.common.tools.Pdf2TextTool;
import com.srj.common.utils.FileUtil;
import com.srj.web.tools.hanlp.DataUnitClean;
import com.srj.web.tools.hanlp.HanlpDateTool;
import com.srj.web.tools.hanlp.ParenthesesClean;
import com.srj.web.tools.hanlp.step.SingleStep2;
import org.junit.Test;
import java.util.List;

public class TestClass {

    @Test
    public void pdfTest(){
        String filePath = "F:\\work\\pdf";
        List<String> pdfList = FileUtil.getFileList(filePath);
        for(String pdfFile:pdfList){
            if(pdfFile.indexOf("pdf")==-1){
                continue;
            }
            String str = Pdf2TextTool.PDF2String(filePath+"/"+pdfFile);
            //清洗日期
            str = HanlpDateTool.handleDateTime(str);
            //继续清洗括号
            //str = ParenthesesClean.cleanMethod(str);
            //找关键行
            //str = DataUnitClean.cleanMethod(str);
            //开始清洗正式数据
            str = SingleStep2.cleanLineMethod(str);
            String txtFileName = pdfFile.substring(0,pdfFile.length()-4);
            FileUtil.writeFile(str,filePath+"/"+txtFileName+".txt",true);
        }

    }


    @Test
    public void test2(){
        String str = "由于本次资本公积金转增股本系重整程序中出资人权益调整的一部分，不同于一般意义上为了分红而单纯增发股票的行为，公司根据《上海证券交易所交易规则》第4.3.2条的规定，调整除权参考价格计算公式。本次资本公积金转增股本后，公司A股股票除权除息参考价格为公司A股股票2021-12-03收盘价格。 ";
        List<Term> list = HanLP.segment(str);
        for(Term t:list){
            System.out.print(t.word+"|"+t.nature+"  ");
        }
    }
}

package com.srj.common.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.common.tools.Pdf2TextTool;
import com.srj.common.utils.FileUtil;
import com.srj.web.tools.hanlp.SingleStepAlpha;
import com.srj.web.tools.hanlp.SingleStepBeta;
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
            //清洗日期和杂项，得到文字版本
            str = SingleStepAlpha.handleDateTime(str);
            //开始清洗正式数据
            str = SingleStepBeta.cleanLineMethod(str);
            String txtFileName = pdfFile.substring(0,pdfFile.length()-4);
            FileUtil.writeFile(str,filePath+"/"+txtFileName+".txt",true);
        }

    }


    @Test
    public void test2(){
        String str = "深圳证券交易所将根据《深圳证券交易所创业板股票上市规则》第10.4.1条第七项的规定";
        List<Term> list = HanLP.segment(str);
        for(Term t:list){
            System.out.print(t.word+"|"+t.nature+"  ");
        }
    }
}

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
            System.out.println("fileName="+pdfFile);
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
        String str = "本公司董事会及全体董事保证本公告内容不存在任何虚假记载、误导性陈述或者重大遗漏，并对其内容的真实性、准确性和完整性承担个别及连带责任。 ";
        List<Term> list = HanLP.segment(str);
        for(Term t:list){
            System.out.print(t.word+"|"+t.nature+"  ");
        }
    }
}

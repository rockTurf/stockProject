package com.srj.common.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.tokenizers.HanLPTokenizer;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.common.tools.Pdf2TextTool;
import com.srj.common.utils.FileUtil;
import com.srj.web.tools.hanlp.HanlpDateTool;
import com.srj.web.tools.hanlp.ParenthesesClean;
import com.srj.web.tools.hanlp.step.CleanDataStepOne;
import org.junit.Test;

import java.io.File;
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
            str = HanlpDateTool.handleDateTime(str);
            //继续清洗括号
            str = ParenthesesClean.cleanMethod(str);
            //清洗数据
            str = CleanDataStepOne.cleanMethod(str);
            String txtFileName = pdfFile.substring(0,pdfFile.length()-4);
            FileUtil.writeFile(str,filePath+"/"+txtFileName+".txt",true);
        }

    }
}

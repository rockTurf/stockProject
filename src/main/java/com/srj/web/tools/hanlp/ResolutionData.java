package com.srj.web.tools.hanlp.step;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.srj.web.util.StringDateUtil;

import java.util.List;

/**
 * 解析数据
 * */
public class ResolutionData {

    //将文档中的数字解析出来
    public static String getNumberData(String text){
        //按行分隔
        String[] array = text.split("\\r?\\n");
        for(String str:array){
            String data = StringDateUtil.DateToString(StringDateUtil.cutDate(str));
            CustomDictionary.add(data,"t 1");
            List<Term> list = HanLP.segment(str);
            boolean b = false;
            for(Term t:list){
                if("m".equals(t.nature.toString())){
                    b = true;
                }
            }
            if(b==true){
                for(Term t:list){
                    System.out.print(t.word+"|"+t.nature+"  ");
                }
            }
        }
        return text;
    }
}

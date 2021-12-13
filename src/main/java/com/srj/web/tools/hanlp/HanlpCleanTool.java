package com.srj.web.tools.hanlp;

import com.hankcs.hanlp.seg.common.Term;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;

/**
 * HANLP 清洗词库的工具包
 * */
public class HanlpCleanTool {

    /**
     * 清洗掉空值的元素
     * */
    public static List<Term> cleanNullValue(List<Term> list){
        Iterator<Term> iter = list.iterator();
        while (iter.hasNext()) {
            Term item = iter.next();
            //首先筛出natrue = w的值
            if ("w".equals(item.nature.toString())){
                //判断是否为空
                String str = item.word;
                str = str.replace(" ", "");
                if(StringUtils.isEmpty(str)){
                    //如果为空，去除这个元素
                    iter.remove();
                }
            }
        }
        return list;
    }
}

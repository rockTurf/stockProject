package com.srj.web.tools.hanlp;

import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * HANLP使用过程中的工具包
 * */
public class HanlpBaseTool {

    /**
     * 分词结果中是否包含指定项
     * */
    public static boolean isTermListInlude(List<Term> list, String target){
        for(Term t:list){
            String nature = t.nature.toString();
            if(target.equals(nature)){
                return true;
            }
        }
        return false;
    }
}

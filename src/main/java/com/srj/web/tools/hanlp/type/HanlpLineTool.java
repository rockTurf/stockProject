package com.srj.web.tools.hanlp.type;

import com.srj.web.tools.hanlp.HanlpConstant;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * 这是个专门处理整行数组的类
 * */
public class HanlpLineTool {

    public static String[] cleanLineMethod(String[] array){
        String[] array2 = cleanLineTag(array);
        String[] array3 = cleanPageTag(array2);
        return array3;
    }

    /**
     * 清理PDF中TAG的类
     * 比如【临时公告】之类的，在每个页的页头上
     * */
    public static String[] cleanLineTag(String[] array){
        //数组先转LIST
        List<String> list = new LinkedList<>();
        Collections.addAll(list,array);
        //取出第一行（也许它是TAG也许不是）
        String first = array[0];
        //把所有疑似TAG的都删掉
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals(first)) {
                iter.remove();
            }
        }
        //把第一行加进去
        list.add(0,first);
        //转数组
        String[] returnArr = list.toArray(new String[list.size()]);
        return returnArr;
    }

    /**
     * 清理PDF中页码的标识
     * */
    public static String[] cleanPageTag(String[] array){
        //数组先转LIST
        List<String> list = new LinkedList<>();
        Collections.addAll(list,array);
        //把所有疑似TAG的都删掉
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            boolean b = ArrayUtils.contains(HanlpConstant.PAGE_TAG,item);
            if(b==true){
                iter.remove();
            }
        }
        //转数组
        String[] returnArr = list.toArray(new String[list.size()]);
        return returnArr;
    }
}

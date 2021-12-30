package com.srj.web.tools.hanlp.step;

import com.srj.web.tools.hanlp.ParenthesesClean;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 第二步处理
 * */
public class SingleStep2 {

    //按行拆分
    public static String cleanLineMethod(String text){
        String[] array = text.split("\\r?\\n");
        String[] array2 = methodOne(array);
        String[] array3 = methodTwo(array2);
        String[] array4 = methodThree(array3);
        //组合句子
        String[] finalArr = array4;
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<finalArr.length;i++){
            String str = finalArr[i];
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    //包含一定关键词就去除
    public static String[] methodOne(String[] array){
        //数组先转LIST
        List<String> list = new LinkedList<>();
        Collections.addAll(list,array);
        Iterator<String> iter = list.iterator();
        //去除【本公司及董事会全体成员保证公告内容的真实、准确和完整，没有虚假记载】
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.indexOf("董事会")!=-1&&item.indexOf("保证")!=-1&&item.indexOf("真实")!=-1) {
                iter.remove();
            }
        }
        //转数组
        String[] returnArr = list.toArray(new String[list.size()]);
        return returnArr;
    }

    //去除括号
    public static String[] methodTwo(String[] array){
        //数组先转LIST
        List<String> list = new LinkedList<>();
        Collections.addAll(list,array);
        for(int i=0;i<list.size();i++){
            String text = list.get(i);
            text = ParenthesesClean.cleanMethod(text);
            list.set(i,text);
        }
        //转数组
        String[] returnArr = list.toArray(new String[list.size()]);
        return  returnArr;
    }

    //去除详见
    public static String[] methodThree(String[] array){
        //数组先转LIST
        List<String> list = new LinkedList<>();
        Collections.addAll(list,array);
        for(int i=0;i<list.size();i++){
            String text = list.get(i);
            text = CleanDataStepOne.cleanMethod(text);
            list.set(i,text);
        }
        //转数组
        String[] returnArr = list.toArray(new String[list.size()]);
        return  returnArr;
    }
}

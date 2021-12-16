package com.srj.web.tools.hanlp;

import org.junit.Test;

/**
 * 清理括号的方法
 * */
public class ParenthesesClean {

    /**
     * 去除括号内的内容
     * */
    public static String cleanMethod(String text){
        text = clearBracket(text, '（', '）');
        text = clearBracket(text, '(', ')');
        return text;
    }
    /**
     * 去除两符号间内容
     * @param context
     * @param left
     * @param right
     * @return
     */
    public static String clearBracket(String context, char left, char right) {
        int head = context.indexOf(left);
        if (head == -1) {
            return context;
        } else {
            int next = head + 1;
            int count = 1;
            do {
                if (context.charAt(next) == left) {
                    count++;
                } else if (context.charAt(next) == right) {
                    count--;
                }
                next++;
                if (count == 0) {
                    String temp = context.substring(head, next);
                    context = context.replace(temp, "");
                    head = context.indexOf(left);
                    next = head + 1;
                    count = 1;
                }
            } while (head != -1);
        }
        return context;
    }

    @Test
    public void test(){
        String str = "2021年11月18日，湖北省十堰市中级人民法院（以下简称“十堰中院”）\n" +
                "依法裁定受理华昌达智能装备集团股份有限公司（以下简称“华昌达”或“公司”）\n" +
                "重整一案，并于同日指定北京市金杜（深圳）律师事务所担任公司管理人，负责\n" +
                "重整期间相关工作。具体内容详见公司披露于《证券时报》《中国证券报》《上";
        str = cleanMethod(str);
        System.out.println(str);
    }
}

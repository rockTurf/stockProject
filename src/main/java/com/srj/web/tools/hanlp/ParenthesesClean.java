package com.srj.web.tools.hanlp;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 清理括号的方法
 * */
public class ParenthesesClean {

    //左括号
    private static final String BRACKETS_FLAG_L = "BRACKETS_FLAG_LEFT_L_142857";
    //右括号
    private static final String BRACKETS_FLAG_R = "BRACKETS_FLAG_LEFT_R_142857";

    /**
     * 去除括号内的内容
     * */
    public static String cleanMethod(String text){
        //把所有括号都转译为中文括号
        text = text.replaceAll("\\(","（");
        text = text.replaceAll("\\)","）");
        //再将所有括号都转为标识码（处理后再转回来）
        text = text.replaceAll("（",BRACKETS_FLAG_L);
        text = text.replaceAll("）",BRACKETS_FLAG_R);
        text = clearBracket(text, '（', '）');
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
                    System.out.println(temp);
                    //如果其不为序号，则去除
                    boolean b = ArrayUtils.contains(HanlpConstant.NUMBER_TAG,temp);
                    if(b==false){
                        context = context.replace(temp, "");
                        head = context.indexOf(left);
                        next = head + 1;
                        count = 1;
                    }
                }
            } while (head != -1);
        }
        return context;
    }

    /**
     * 提取中括号中内容，忽略中括号中的中括号
     * @param msg
     * @return
     */
    public static List<String> extractMessage(String msg) {

        List<String> list = new ArrayList<String>();
        int start = 0;
        int startFlag = 0;
        int endFlag = 0;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '（') {
                startFlag++;
                if (startFlag == endFlag + 1) {
                    start = i;
                }
            } else if (msg.charAt(i) == '）') {
                endFlag++;
                if (endFlag == startFlag) {
                    list.add(msg.substring(start + 1, i));
                }
            }
        }
        return list;
    }


    @Test
    public void test(){
        String str = "1、深圳赫美集团股份有限公司（以下简称“赫美集团”或“公司”）于2021-11-29收到深圳市中级人民法院（以下简称“深圳中院”）送达的（2020)粤03破申827号《民事裁定书》及（2020)粤03破申827号《决定书》。深圳中院裁定受理债权人深圳市华远显示器件有限公司（以下简称“华远显示”）对公司的重整申请，并指定深圳诚信会计师事务所（特殊普通合伙）、北京市君合（深圳）律师事务所共同担任公司管理人。";
        List<String> list = extractMessage(str);
        for(String ss:list){
            System.out.println(ss);
        }
        /*str = cleanMethod(str);
        System.out.println(str);*/
    }
}

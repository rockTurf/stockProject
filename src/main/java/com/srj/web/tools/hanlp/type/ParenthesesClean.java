package com.srj.web.tools.hanlp.type;

import com.srj.web.tools.hanlp.HanlpConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 清理括号的方法
 * */
public class ParenthesesClean {

    /**
     * 去除括号内的内容
     * */
    public static String cleanMethod(String text){
        //把所有括号都转译为中文括号
        text = text.replaceAll("\\(","（");
        text = text.replaceAll("\\)","）");
        //取出括号的内容
        List<String> list = extractMessage(text);
        for(String str:list){
            //判断是否为不需要清除的内容
            boolean b = ArrayUtils.contains(HanlpConstant.PAGE_TAG,str);
            if(b==false){
                text = text.replaceAll("（"+str+"）","");
            }
        }
        return text;
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

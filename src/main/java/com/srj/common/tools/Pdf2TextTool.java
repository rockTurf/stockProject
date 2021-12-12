package com.srj.common.tools;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
public class Pdf2TextTool {
    /**
     * 读取PDF转String
     * */
    public static String PDF2String(String pdfFile){
        //加载测试文档
        PdfDocument pdf = new PdfDocument(pdfFile);
        //实例化StringBuilder类
        StringBuilder sb = new StringBuilder();
        //定义一个int型变量
        int index = 0;
        //遍历PDF文档中每页
        PdfPageBase page;
        for (int i= 0; i<pdf.getPages().getCount();i++) {
            page = pdf.getPages().get(i);
            //调用extractText()方法提取文本
            sb.append(page.extractText(true));
        }
        //System.out.println(sb);
        pdf.close();
        //处理返回的String
        String str = sb.toString();
        str = HandlePdfString(str);
        return str;
    }

    /**
     * 处理返回的字符串
     * */
    public static String HandlePdfString(String text){
        text = text.replaceAll("Evaluation Warning : The document was created with Spire.PDF for Java.","");
        text = dealRedundantSpaceAndBlankLine(text);
        return text;
    }


    /**
     * 移除多余空行和空格
     */
    public static String dealRedundantSpaceAndBlankLine(String content)
    {
        if (content == null || content.length() == 0)
        {
            return "";
        }
        StringBuilder strAfterRemoveCRSB = new StringBuilder();
        for (int i = 0; i < content.length(); i++)
        {
            if (content.charAt(i) != '\r')
                strAfterRemoveCRSB.append(content.charAt(i));
        }
        String strAfterRemoveCR = strAfterRemoveCRSB.toString();
        if (strAfterRemoveCR == null || strAfterRemoveCR.length() == 0)
        {
            return "";
        }
        StringBuilder resultSB = new StringBuilder();
        String[] lines = strAfterRemoveCR.split("\n");
        int blankCount = 0;
        for (String line : lines)
        {
            if (line == null)
            {
                continue;
            }
            String lineTrim = line.trim();
            if ("".equals(lineTrim))
            {
                blankCount++;
                if (blankCount <= 2) {
                    //resultSB.append("<br/>");
                }
            } else {
                blankCount = 0;
                resultSB.append(dealSpace4OneLine(line)).append("<br/>");
            }
        }
        resultSB.deleteCharAt(resultSB.length() - 1);
        return resultSB.toString();
    }

    /**
     * 移除1行中的多余空格
     */
    public static String dealSpace4OneLine(String line)
    {
        //System.out.println(line);
        if (line == null || "".equals(line)) {
            return "";
        }
        int spaceCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char curChar = line.charAt(i);
            if (curChar == ' ')
            {
                spaceCount++;
                if (spaceCount <= 5) {
                    sb.append(' ');
                }
            } else {
                spaceCount = 0;
                sb.append(curChar);
            }
        }
        return sb.toString();
    }





    public static void main(String[]args) throws Exception {
        String pdffile = "F:\\work\\ST华讯 华讯方舟股份有限公司关于预重整债权申报的通知-20210903.pdf";
        //PDF2String(pdffile);
        System.out.println(PDF2String(pdffile));
    }
}

package com.srj.web.util;

public class TxtUtil {
	
	
	//处理txt字符串(将tab替换为*并去空格返回字符串)
	public static String getLineTxt(String str){
		String[] lineArr = str.split("\\t");			
		String newline="";
		for (int i = 0; i < lineArr.length; i++){
		    if (i<lineArr.length){
		        newline+=lineArr[i]+"*";
		    }else{
		    	newline+=newline+lineArr[i];
		    }
		}
		return newline.replaceAll("\\s*", "");
	}
	
	//处理大数值，把逗号去掉
	public static String getBigInteger(String str){
		return str.replaceAll(",", "");
	}

	/**
	 * 去除文章内容页页面代码里的HTML标签
	 * Created by yanyl on 2018/6/4.
	 */
	public static String delHtmlTags(String htmlStr) {
		if(StringUtil.isEmpty(htmlStr)){
			return htmlStr;
		}
		//定义script的正则表达式，去除js可以防止注入
		String scriptRegex="<script[^>]*?>[\\s\\S]*?<\\/script>";
		//定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
		String styleRegex="<style[^>]*?>[\\s\\S]*?<\\/style>";
		//定义HTML标签的正则表达式，去除标签，只提取文字内容
		String htmlRegex="<[^>]+>";
		//定义空格,回车,换行符,制表符
		String spaceRegex = "\\s*|\t|\r|\n";

		// 过滤script标签
		htmlStr = htmlStr.replaceAll(scriptRegex, "");
			// 过滤style标签
		htmlStr = htmlStr.replaceAll(styleRegex, "");
		// 过滤html标签
		htmlStr = htmlStr.replaceAll(htmlRegex, "");
		// 过滤空格等
		htmlStr = htmlStr.replaceAll(spaceRegex, "");
		return htmlStr.trim(); // 返回文本字符串
		}
		/**
		 * 获取HTML代码里的内容
		 * @param htmlStr
		 * @return
		 */
		public static String getTextFromHtml(String htmlStr){
			//去除html标签
			htmlStr = delHtmlTags(htmlStr);
			//去除空格" "
			htmlStr = htmlStr.replaceAll(" ","");
			return htmlStr;
		}


}

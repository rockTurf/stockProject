package com.srj.web.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONException;
import org.apache.poi.ss.formula.functions.T;


public class StringUtil {
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf((val.toString().trim()));
		} catch (Exception e) {
			return 0D;
		}
	}
	
	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}

	/**
	 * 将驼峰风格替换为下划线风�?
	 */
	public static String camelhumpToUnderline(String str) {
		final int size;
		final char[] chars;
		final StringBuilder sb = new StringBuilder(
				(size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
		char c;
		for (int i = 0; i < size; i++) {
			c = chars[i];
			if (isLowercaseAlpha(c)) {
				sb.append(toUpperAscii(c));
			} else {
				sb.append('_').append(c);
			}
		}
		return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
	}

	/**
	 * 将下划线风格替换为驼峰风�?
	 */
	public static String underlineToCamelhump(String name) {
		if(!name.contains("_")) return name;
		char[] buffer = name.toCharArray();
		int count = 0;
		boolean lastUnderscore = false;
		for (int i = 0; i < buffer.length; i++) {
			char c = buffer[i];
			if (c == '_') {
				lastUnderscore = true;
			} else {
				c = (lastUnderscore && count != 0) ? toUpperAscii(c)
						: toLowerAscii(c);
				buffer[count++] = c;
				lastUnderscore = false;
			}
		}
		if (count != buffer.length) {
			buffer = subarray(buffer, 0, count);
		}
		return new String(buffer);
	}

	public static char[] subarray(char[] src, int offset, int len) {
		char[] dest = new char[len];
		System.arraycopy(src, offset, dest, 0, len);
		return dest;
	}

	public static boolean isLowercaseAlpha(char c) {
		return (c >= 'a') && (c <= 'z');
	}

	public static char toUpperAscii(char c) {
		if (isLowercaseAlpha(c)) {
			c -= (char) 0x20;
		}
		return c;
	}

	public static char toLowerAscii(char c) {
		if ((c >= 'A') && (c <= 'Z')) {
			c += (char) 0x20;
		}
		return c;
	}
	
	public static boolean isEmpty(String str){
		if(str == null || str.trim().length() == 0)
			return true;
		return false;			
	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			return "".equals(str.trim());
		}
	}
	public static List<String> String2List(String str){
		if(str==null){
			return null;
		}else{
			String[] array = str.split(",");
			List<String> abcList = new ArrayList<String>();
			for (String s : array)
			{
			abcList.add(s);
			}
			return abcList;
		}
	}
	
	//字符串转数组
	public static Long[] String2LongArray(String str){
		if(str!=null){
			String [] result = str.split(",");
			Long[] array=new Long[result.length];
	        for(int i=0;i<result.length;i++){
	        	array[i]=Long.valueOf(result[i]);
	        }
	        return array;
		}else{
			return null;
		}
	}
	
	public static <E> String list2String(List<E> list){
        if (list==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (E element : list) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(element.toString());
        }
        return result.toString();
  }
	//list去重复
	public static void Dereplication(List<?> list){
		for (int i = 0; i < list.size(); i++)  //外循环是循环的次数
        {
            for (int j = list.size() - 1 ; j > i; j--)  //内循环是 外循环一次比较的次数
            {
                if (list.get(i) == list.get(j))
                {
                	list.remove(j);
                }
            }
        }
	}

	//判断是否是数字
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
    
    /**
     	* 两个整数除，保留N位小数，并转换成百分数
     * */
    public static String getPercent(int count,int total,int n){
    	float num = (float)count/total;
    	NumberFormat nt = NumberFormat.getPercentInstance();
    	nt.setMinimumFractionDigits(n);//不保留小数
    	return nt.format(num);
    }

    /**
	 * 判断字符串有几个指定字符
	 * */
	public static int getStringCount(String str, String key) {
		if (str == null || key == null || "".equals(str.trim()) || "".equals(key.trim())) {
			return 0;
		}
		int count = 0;
		int index = 0;
		while ((index = str.indexOf(key, index)) != -1) {
			index = index + key.length();
			count++;
		}
		return count;
	}

	/**
	 * 判断字符串是否包含数组元素中的任意一个
	 * */
	public static boolean isIncludeArray(String text, String array[]) {
		for(String str:array){
			if(text.indexOf(str)!=-1){
				return true;
			}
		}
		return false;
	}

}
	


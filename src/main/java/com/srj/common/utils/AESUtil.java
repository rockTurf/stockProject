package com.srj.common.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.srj.common.constant.Constant;
import com.srj.web.util.StringUtil;

/**
 * AES加解密
 * */
public class AESUtil {

    private static boolean IF_DE_FIRST = true;

    /**
     * 加密
     * */
    public static String EncryptBase64(String value){
        if(StringUtil.isEmpty(value)){
            return value;
        }
        AES aes = SecureUtil.aes(Constant.AES_KEYS);
        //加密前是否需要先解密，判断是不是已经加过密了
        if(IF_DE_FIRST = true){
            String deValue = DecryptBase64(value);
            if(!deValue.equals(value)){
                //不等于，说明解密成功了，那么就直接返回
                return value;
            }
        }
        String returnStr = aes.encryptBase64(value);
        System.out.println("加密原文本："+value+"，加密后文本："+returnStr);
        return returnStr;
    }

    /**
     * 解密
     * */
    public static String DecryptBase64(String value){
        if(StringUtil.isEmpty(value)){
            return value;
        }
        AES aes = SecureUtil.aes(Constant.AES_KEYS);
        try{
            String returnStr = aes.decryptStr(value);
            System.out.println("解密原文本："+value+"，解密后文本："+returnStr);
            return returnStr;
        }catch (RuntimeException e){
            System.out.println("解密失败的原文本为："+value);
            return value;
        }
    }

}

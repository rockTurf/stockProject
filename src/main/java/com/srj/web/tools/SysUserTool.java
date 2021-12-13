package com.srj.web.tools;

import com.srj.common.utils.AESUtil;
import com.srj.web.sys.model.SysUser;

import java.util.List;

/**
 * 处理用户的类
 * */
public class SysUserTool {

    /**
     * 给用户的手机号进行AES加密
     * */
    public static SysUser EncryptPhone(SysUser user){
        String phone = user.getPhone();
        //加密
        String aesPhone = AESUtil.EncryptBase64(phone);
        //塞回去
        user.setPhone(aesPhone);
        return user;
    }

    /**
     * 给用户的手机号进行AES解密
     * */
    public static SysUser DecryptPhone(SysUser user){
        String phone = user.getPhone();
        //解密
        String desPhone = AESUtil.DecryptBase64(phone);
        //塞回去
        user.setPhone(desPhone);
        return user;
    }

    /**
     * 解密处理list
     * */
    public static List<SysUser> DecryptPhoneList(List<SysUser> list){
        for(int i=0;i<list.size();i++){
            SysUser item = list.get(i);
            item = DecryptPhone(item);
            list.set(i,item);
        }
        return list;
    }
}

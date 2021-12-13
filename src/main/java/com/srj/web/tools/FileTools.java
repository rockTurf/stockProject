package com.srj.web.tools;

import java.util.LinkedList;
import java.util.List;

/**
 * 文件配套工具类
 * */
public class FileTools {
    /**
     * 根据map取出所有file文件
     * */
    public static List<String> GetFileListByMapString(String str){
        //1_fileAlpha=5247c86f-3d52-4165-a904-842873af9149.xlsx,2_fileBeta=018253b9-83ef-4671-9031-1f9b768c2b64.xlsx
        List<String> list = new LinkedList<>();
        //1.逗号分隔
        String array[] = str.split(",");
        for(int i=0;i<array.length;i++){
            //1_fileAlpha=5247c86f-3d52-4165-a904-842873af9149.xlsx
            //2.按等号分为两部分
            String fileMap = array[i];
            String fileArray[] = fileMap.split("=");
            String fileName = fileArray[1];
            list.add(fileName);
        }
        return list;
    }

}

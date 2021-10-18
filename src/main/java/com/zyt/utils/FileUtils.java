package com.zyt.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.utils
 * @ClassName: FileUtils
 * @Author: zhou_yangtao@yeah.net
 * @Description: 文件上传工具
 * @Date: 9:49 2021/1/19
 * @Version: 1.0
 */
public class FileUtils {

    public static void uploadFile(byte[] file,String filePath,String fileName){
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(file);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }














}

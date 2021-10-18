package com.zyt.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.utils
 * @ClassName: Md5Utils
 * @Author: zhou_yangtao@yeah.net
 * @Description: md5加密
 * @Date: 19:56 2021/3/15
 * @Version: 1.0
 */
public class Md5Utils {
    /**
     * 生成MD5加密串
     */
    public static String getMD5(String message) {
        String md5 = "";
        try {
            //创建一个md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageByte = message.getBytes(StandardCharsets.UTF_8);
            //获得MD5字节数组,16*8=128位
            byte[] md5Byte = md.digest(messageByte);
            //转换为16进制字符串
            md5 = ByteUtils.byteFormSecondToHex(md5Byte);
        } catch (Exception e) {
            //输出到日志文件中
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * 验证方法
     * @param text 明文
     * @param md5 密文
     * @return 对比结果
     */
    private static boolean verify(String text,String md5){
        return md5.equals(getMD5(text));
    }
}

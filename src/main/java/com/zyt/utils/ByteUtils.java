package com.zyt.utils;

import java.io.*;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.utils
 * @ClassName: ByteUtils
 * @Author: zhou_yangtao@yeah.net
 * @Description: 字节相关工具类
 * @Date: 19:57 2021/3/15
 * @Version: 1.0
 */
public class ByteUtils {
     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:二进制字节转为十六进制
      * @Return:
      * @Exception:
      * @Date: 2021/3/15 19:57
      * @Param: * @param null
      * @Return:
      */
      public static String byteFormSecondToHex(byte[]bytes){
          StringBuffer stringBuffer = new StringBuffer();
          int counts = 0;
          for(byte _byte : bytes){
              counts = _byte;
              if (counts < 0){
                  counts += 256;
              }
              if (counts < 16){
                  stringBuffer.append("0");
              }
              stringBuffer.append(Integer.toHexString(counts));
          }
          return stringBuffer.toString().toUpperCase();
      }

       /**
        * @Method:
        * @Author: zhou_yangtao@yeah.net
        * @Version  1.0
        * @Description:object对象转为数组
        * @Return:
        * @Exception:
        * @Date: 2021/3/15 20:01
        * @Param: * @param null
        * @Return:
        */
    /**
     * Object对象转byte[]
     */
    public static byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            //开始写入输出流
            oo.writeObject(obj);
            //输出流转byte
            bytes = bo.toByteArray();
        } catch (Exception e) {
            //输出到日志文件中
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                assert bo != null;
                bo.close();
                assert oo != null;
                oo.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * byte[]转Object对象
     */
    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            //读取输入流
            obj = oi.readObject();
        } catch (Exception e) {
            //输出到日志文件中
            e.printStackTrace();
        } finally {
            //关流
            try {
                assert bi != null;
                bi.close();
                assert oi != null;
                oi.close();
            } catch (IOException e) {
                //输出到日志文件中
                e.printStackTrace();
            }
        }
        return obj;
    }

}

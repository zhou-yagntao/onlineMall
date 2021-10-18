package com.zyt;


import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.zyt.component.SmsComponent;

import com.zyt.constant.EncrypteDecrypte;
import com.zyt.utils.AESUtils;
import com.zyt.utils.DateUtils;
import com.zyt.utils.HttpUtils;
import com.zyt.utils.RSAUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Unit test for simple App.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest
{
    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private SmsComponent smsComponent;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;



    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
//        final  String sale = "zhou_yangtao_aes";
//        String pass = "liuBei_0427%";
//        System.out.println("加密前数据:"+pass);
//        String encrypt = AESUtils.encrypt(pass, sale);
//        System.out.println("加密后数据:"+encrypt);
//        String decrty = AESUtils.decrypt(encrypt,sale);
//        System.out.println("解密后数据:"+decrty);
//        this.smsComponent.sendSmsCode("18373378522","4562");
//        String host = "https://zwp.market.alicloudapi.com";
//        String path = "/sms/edittemplete";
//        String method = "POST";
//        String appcode = "25aeab38c1af439aaa9f94906df74b02";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("content", "本次确认验证码为#code#,有效时间为5分钟,任何索取确认码均为危险行为，为了您的资金以及账户安全，切勿泄漏!");
//        querys.put("signature", "【DrinkOnlineMall】");
//        Map<String, String> bodys = new HashMap<String, String>();
//        try {
//            /**
//             * 重要提示如下:
//             * HttpUtils请从
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//             * 下载
//             *
//             * 相应的依赖请参照
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//             */
//            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
//            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(LocalDateTime.now().getSecond()+ IdWorker.getId());
//        System.out.println(LocalDateTime.now().atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli());
//        String delayAddress = "陕西省安康市汉滨区高新现代城恒大御景园10栋2602室";
//        String province = delayAddress.substring(0,3);
//        String city = delayAddress.substring(3,6);
//        String region = delayAddress.substring(6,9);
//        System.out.println(province+"\t"+city+"\t"+region);
//        Date date  =new Date();
//        String str = DateUtils.dateString(date,"yyyy-MM-dd HH:mm:ss");
//        System.out.println(str);
//        Date newDate = DateUtils.stringDate(str,"yyyy-MM-dd HH:mm:ss");
//        System.out.println(newDate);
//        System.out.println(LocalDateTime.now().getSecond());
//          String date = "2021-03-06T10:00:00.000Z";
//          date = date.replace("Z", " UTC");
//          System.out.println(date);
//          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//          Date d = format.parse(date);
//          System.out.println(d);
//
//        String passWord = "123456";
//        Map<String, Object> genKeyPair = RSAUtil.getGenKeyPair();
//        System.out.println(genKeyPair.keySet());
//        Set<String> keys = genKeyPair.keySet();
//        for (String key:keys){
//            System.out.println("当前"+key+"对应的值为:"+genKeyPair.get(key).toString());
//        }
//        String pulic_key = genKeyPair.get("publicKey").toString();
//        byte[] encrpty_pass = RSAUtil.encryptByPublicKey(passWord.getBytes(),pulic_key);
//        System.out.println("公钥为:"+pulic_key+"\t"+"加密数据:"+encrpty_pass.toString());
//        System.out.println("-----------------------------------");
//        String private_key = genKeyPair.get("privateKey").toString();
//        String dec_password = RSAUtil.decryptByPrivateKey(encrpty_pass,private_key).toString();
//        System.out.println("解密数据为:"+dec_password);
//        String pass = "justin2021";
//        BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
//        String password = bCryptPasswordEncoder.encode(pass);
//        System.out.println(password);

//        String str = "libai_0427";
//        String res1 = AESUtils.encrypt(str, EncrypteDecrypte.PASSWORD_ENCRYPTE_DECRYPTE_SALE);
//        System.out.println(res1);
//        String res2 = AESUtils.decrypt(res1,EncrypteDecrypte.PASSWORD_ENCRYPTE_DECRYPTE_SALE);
//        Sytring host = "https://zwp.market.alicloudapi.com";
//            String path = "/sms/sendv2";
//            String method = "GET";
//            String appcode = "25aeab38c1af439aaa9f94906df74b02";
//            Map<String, String> headers = new HashMap<String, String>();
//            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//            headers.put("Authorization", "APPCODE " + appcode);
//            Map<String, String> querys = new HashMap<String, String>();
//            querys.put("content", "【儿童教务】您正在登录验证,验证码为3256 ,60s内有效,请尽快验证。");
//            querys.put("mobile", "18373378522");
//
//
//            try {
//                /**
//                 * 重要提示如下:
//                 * HttpUtils请从
//                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//                 * 下载
//                 *
//                 * 相应的依赖请参照
//                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//                 */stem.out.println(res2);
//            S
//                HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//                System.out.println(response.toString());
//                //获取response的body
//                //System.out.println(EntityUtils.toString(response.getEntity()));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

    }

}

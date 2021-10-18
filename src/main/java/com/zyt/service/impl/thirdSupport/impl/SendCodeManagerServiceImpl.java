package com.zyt.service.impl.thirdSupport.impl;

import com.sun.mail.util.MailSSLSocketFactory;
import com.zyt.component.SmsComponent;
import com.zyt.constant.AutoServerConstant;
import com.zyt.service.thirdSupport.SendCodeManagerService;
import com.zyt.utils.HttpUtils;
import com.zyt.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.thirdSupport.impl.SendCodeManagerService
 * @ClassName: SendCodeManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 第三方短信服务实现层
 * @Date: 14:27 2021/2/19
 * @Version: 1.0
 */
@Service(value = "sendCodeManagerService")
public class SendCodeManagerServiceImpl  implements SendCodeManagerService {

    private Logger logger = LoggerFactory.getLogger(SendCodeManagerService.class);

    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @Method: SendCode
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:发送验证码服务
     * @Return: int
     * @Exception:
     * @Date: 2021/2/19 14:28
     * @Param: * @param phone
     * @param code
     * @Return: int
     */
    @Override
    public int SendCode(String phone, int code) {
        //首先进入服务层获取验证码
        String redisCode =  this.redisUtil.get(AutoServerConstant.SMS_CODE_CACHE_PREFIX+phone);
        if(!StringUtils.isEmpty(redisCode)){
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - l < 60000){
                return  0;
            }
        }

         String scode = code+"_"+System.currentTimeMillis();
        //将验证码以手机号为键 验证码为值 并设置过期时间  保存到redis缓存
        this.redisUtil.set(AutoServerConstant.SMS_CODE_CACHE_PREFIX+phone,scode,1, TimeUnit.MINUTES);
        String receiveEmail = "1737748190@qq.com";
        //我们使用线程来专门发送邮件，防止出现耗时，和网站注册人数过多的情况；
        SendEmailThread sendEmailThread = new SendEmailThread(receiveEmail,code);
        //启动线程，线程启动之后就会执行run方法来发送邮件
        sendEmailThread.start();
        return 1;

    }

//    public void sendSmsCode(String phone,String code){
//        String host = "https://zwp.market.alicloudapi.com";
//        String path = "/sms/sendv2";
//        String method = "GET";
//        String appcode = "25aeab38c1af439aaa9f94906df74b02";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("content", "【儿童教务】您正在登录验证,验证码为"+code+ ",60s内有效,请尽快验证。");
//        querys.put("mobile", "18373378522");
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
//            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//            System.out.println(response.toString());
//            //获取response的body
//            //System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

class SendEmailThread extends  Thread{
    //用于给用户发送邮件的邮箱
    private String from = "1737748190@qq.com";
    //邮箱的用户名
    private String username = "1737748190@qq.com";
    //邮箱的密码
    private String password = "kvdgwqwkidulbddf";
    //发送邮件的服务器地址
    private String host = "smtp.qq.com";

    private String receiveEmail = null;
    private  int verificationNum;

    public SendEmailThread(String reEmail,int verifNum){
        this.receiveEmail = reEmail;
        this.verificationNum = verifNum;
    }

    @Override
    public void run() {
        super.run();
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host",host);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.smtp.timeout","300000");
        /*
         * qq邮箱设置ssl加密
         * */
        try {
            MailSSLSocketFactory sf  = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
            //1、创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、授权码
                    return new PasswordAuthentication(username, password);
                }
            });
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);

            //通过session得到transport对象
            Transport ts = session.getTransport();

            //使用邮箱的用户名和授权码连上邮件服务器
            ts.connect(host, username, password);

            //4、创建邮件
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); //发件人
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(receiveEmail));
            message.setSubject("用户注册邮箱验证");
            String info = "<h4>尊敬的用户您好:</h4>"+
                    "<p>&nbsp;&nbsp;&nbsp;&nbsp;您的注册验证码为</p>"+verificationNum+"<p>请您妥善保管您的验证码，切勿告知他人），请在页面中输入完成验证。</p>"+
                    "<p>安全提示:</p>"+
                    "<p>为保障您的帐户安全，请在 60秒内完成验证，否则验证码将自动失效</p>";
            message.setContent(info, "text/html;charset=UTF-8");
            message.saveChanges();
            //发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

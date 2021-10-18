package com.zyt.constant;

import java.security.PublicKey;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.constant
 * @ClassName: AlipayConstant
 * @Author: zhou_yangtao@yeah.net
 * @Description: 支付宝支付相关变量数据
 * @Date: 10:37 2021/3/4
 * @Version: 1.0
 */
public class AlipayConstant {

    //在支付宝创建的应用的id
    public static  final String ALIPAY_APP_ID = "2021000117617379";

    //支付宝网关
    public  static final String ALIPAY_GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    //支付宝商户支付私钥
    public  static  final  String  ALIPAY_MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvikgzaahtl49DLw0phcrGbxjOv3HKj7XpWsSbgBMHtR+rBAeb/HUU1eNRGD6tlSwMD8Ym/EANucdUXHGbrUCeSk8DRNKF3yQxlv1aO7KuVhYnJxZV3aMlXlXtNAV7lgTh4OklTx0v1Itz8Odf+hbsKwbEzFcCPp+/9m5dHtQPLkNo7ySI3am2yFfMBwGJaICO6RFtFgQOlNg42AtAVfR+CL/dBQKkWZ0cgQo+xibW8VtW0LzAvV/9nwJUT0pkUlABQyoPyknUmGFAcIwVcLiLVvBUq/tDoFXkogwX7NenSvM4iIvajrFuqpyA+L0oxjf6NSTi96ATnOwafLojg0HRAgMBAAECggEBAIq5bXWz+jchn0Yo2O4HnchT4/b/tJ2wFMLlyJpbDK76pJwyAxFy4dQldA+SaXYkfTrK/H2yu6wdNTmmFOJCeN6fOvIKYZUKU0AdYvr8UqkTv04RxcT18wiFFMLwaaTRWepoI9goPZifD9YAe9mNerAJEMBV/Cz58KVzyqQsyMl1JvpPwehXJorcPLNGe/lN+olPKBGog3FVr1ArNL5MmfAseR57di7jgOAv0pGzgDRQBe8m5A2FWnvUQ69g15nsubCnsw49UZ6rTr04H+RUvbE7ewkty4uKTsx7RLbhB1LYHux0O7M2iE3AnoJkIKRTAl2Io7TDKgK0URGpD2i9AEECgYEA2oGh+WLi9XDgA+WxNxaXlY9DZC7TxWM8oV+HJMC7YhyZBb+a00LgUbqhVbV8M9dvJNvu+Rqa1F5Ab4NEzqup70rfjd3ifA6Kf08zbwj8eBsJAX1pNeLYPq3/N/QQAwwMzSwokDxyBrrD5PAtRAAyNsld8aIxkf6Htr/MRcq5gUsCgYEAzalD2upiExY0h2MJjlgxsyDQsUXE3nPcSLF75g5Yu1zlmQDDXtwqrz/I1fthHKM286gicL7XnJ6+MLuB3jVu8Zw0dPBruB/AoPiPTDN/3gbWiD5sgT0+RcLiVBcFugO3scUabSXP0Vq6Shimmrpk1iphW0h9deSIVp6nHfn7c9MCgYA8W2iZkbYtpCNNEEqWc/glSPCNoN3AfXwG+3NCNLbOECY4z0d+Kc/643mjSkFntI+v98yYJMA1/17/Lpx4FjUXs2/zKSJhsEaTihYkCrBqoNUpAzJnvySKk9eW5iTyIob6ucLurLO2pipsd+eZDBDSz2bS00GsGUn1sh9D/A6n5wKBgDqoQFY762UOPEUxFhSnSuWQQnWNNK1N1lJVbMUc6mnmAQY2lYKEwRfi1WFSpUwJsS6lkc0eYVimXhCAKnY6Pz5iEwQXXmHRjJtcyWIufhXRVXbedwwVb6FuCCd7uYJodQga84KV+kP34JIpnbsb1o7O352tezsYx1oNPhTqq7FJAoGAZ21RDbC4ggvtM9gHxoSH7LJny45xRpIl32oVumpvQXH29Kd3Qlri5fbexHRMlrBDkwGSli84o94aYlwhQw5ZWj6V5OD60PWV4EnUHR80DhxMkiKcy8DY1As11bWvKLLeMQObEmb6CeUsYF0ypQPgHmEUtBDXJKW4aL30FaFwPkw=";

    //支付宝支付共钥
    public  static  final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrEVFMOSiNJXaRNKicQuQdsREraftDA9Tua3WNZwcpeXeh8Wrt+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";

    //签名方式
    public static final  String ALIPAY_SIGN_TYPE = "RSA2";

    //符编码格式
    public  static final  String ALIPAY_CHAR_SET = "utf-8";

    //日志记录目录
    public  static  final  String ALIPAY_LOG_PATH = "E:\\log\\project_log4j2\\pay_log";

    //回调地址
    public static final String ALIPAY_RETURN_URL = "http://127.0.0.1:9999/PersonOrderItems";

    //异步通知回调页面         http://ked1ilzy53.52http.net
    public static  final String ALIPAY_NOTIFY_URL = "http://ked1ilzy53.52http.net/payOrderListner/handleSuccessedAlipayNotify";
    //public static  final String ALIPAY_NOTIFY_URL = "http://127.0.0.1:9000/payOrderListner/handleSuccessedAlipayNotify";

    //超时自动关单
    public static  final String ALIPAY_TIMEOUT_CLOSE = "30m";

    //返回格式
    public static final String ALIPAY_FORMAT = "json";
}

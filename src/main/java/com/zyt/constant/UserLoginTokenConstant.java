package com.zyt.constant;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.constant
 * @ClassName: UserLoginTokenConstant
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户登录token有效时长
 * @Date: 19:43 2021/3/15
 * @Version: 1.0
 */
public class UserLoginTokenConstant {

    public static  final  int USER_LOGIN_TOKEN_ECRPIRATION = 24 * 60 * 60 * 1000;

    public static  final String USER_LOGIN_TOKEN_HEADER = "Authorization";

    public static  final String USER_LOGIN_TOKEN_PREFIX = "zhou_";

    public  static final String  USER_LOGIN_TOKEN_ISSUSER = "zhou_yangtao";

    public static  final  String USER_LOGIN_TOKEN_SECRET_KEY = "zhou_yangtao@yeah.net";

    //配置不需要认证的接口
    public static final String   USER_LOGIN_NOT_ANTMATCHERS = "user/toLogin,/index/**,/error/**,/favicon.ico,";


}

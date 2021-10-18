package com.zyt.exception;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.exception
 * @ClassName: BadCredentialsException
 * @Author: zhou_yangtao@yeah.net
 * @Description: 密码不匹配异常
 * @Date: 13:06 2021/3/16
 * @Version: 1.0
 */
public class BadCredentialsException extends  RuntimeException{


    public BadCredentialsException(){
        super("密码不匹配");
    }

}

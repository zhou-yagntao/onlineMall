package com.zyt.exception;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.exception
 * @ClassName: UsernameNotFoundException
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 12:45 2021/3/16
 * @Version: 1.0
 */
public class UsernameNotFoundException extends  RuntimeException{

    public UsernameNotFoundException(){
        super("用户名不存在");
    }

}

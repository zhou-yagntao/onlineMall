package com.zyt.service.userService;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service
 * @ClassName: UserLoginManager
 * @Author: zhou_yangtao@yeah.net
 * @Description: 客户登录管理服务层
 * @Date: 12:29 2021/2/15
 * @Version: 1.0
 */
public interface UserLoginManagerService {

    //验证用户登录
    public int GetUserLoginResult(String [] loginInfo,String code);
}

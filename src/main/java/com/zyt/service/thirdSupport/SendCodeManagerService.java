package com.zyt.service.thirdSupport;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.thirdSupport
 * @ClassName: SendCodeManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 验证码服务层
 * @Date: 14:26 2021/2/19
 * @Version: 1.0
 */
public interface SendCodeManagerService {

    public int SendCode(String phone,int code);
}

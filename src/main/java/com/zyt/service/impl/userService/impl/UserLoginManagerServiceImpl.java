package com.zyt.service.impl.userService.impl;

import com.zyt.constant.EncrypteDecrypte;
import com.zyt.mapper.UserMapper;
import com.zyt.service.userService.UserLoginManagerService;
import com.zyt.utils.AESUtils;
import com.zyt.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: UserLoginManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户登录管理服务层实现层
 * @Date: 12:30 2021/2/15
 * @Version: 1.0
 */
@Service(value = "userLoginManagerService")
public class UserLoginManagerServiceImpl implements UserLoginManagerService {

    private Logger logger = LoggerFactory.getLogger(UserLoginManagerServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    /**
     * @Method: GetUserLoginResult
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:进行用户登录验证模块
     * @Return: int
     * @Exception:
     * @Date: 2021/2/15 12:32
     * @Param: * @param loginInfo
     * @param code
     * @Return: int
     */
    @Override
    public int GetUserLoginResult(String[] loginInfo, String code) {
        for (int i = 0; i < loginInfo.length; i++) {
            logger.info("传入的参数为:"+loginInfo[i]);
        }
        logger.info("验证码信息为:"+code);
        //判断验证码是否输入的正确
        if (!loginInfo[3].equals(code)){
            //返回1表示验证码不同
            logger.info("返回验证码验证信息");
            return 1;
        }
        //根据用户名向缓存查询用户登录密码
        String userLoginPass = this.redisUtil.get(loginInfo[0]);
        if (userLoginPass == null){
            //在数据库进行查询
            String userLoginPassInfo = this.userMapper.getUserLoginPass(loginInfo[0]);
            //查询到数据后放入到缓存中农
            this.redisUtil.set(loginInfo[0],userLoginPassInfo);
            userLoginPass = userLoginPassInfo;
        }
        String decryptPass = null;
        try {
            //获得缓存中的数据后进行解密处理
            decryptPass = AESUtils.decrypt(userLoginPass, EncrypteDecrypte.PASSWORD_ENCRYPTE_DECRYPTE_SALE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断我们输入的密码与后台解密后的密码是否一致 若不一致 则返回标志结果
        if(!loginInfo[1].equals(decryptPass)){
            return  2;
        }else{
            return 3;
        }

    }
}

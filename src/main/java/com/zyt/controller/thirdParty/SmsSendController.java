package com.zyt.controller.thirdParty;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.thirdSupport.SendCodeManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.thirdParty
 * @ClassName: SmsSendController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 短信发送控制层
 * @Date: 12:30 2021/2/19
 * @Version: 1.0
 */
@Controller
@RequestMapping("/smsSendController")
public class SmsSendController {

   private Logger logger = LoggerFactory.getLogger(SmsSendController.class);

   @Autowired
   private SendCodeManagerService sendCodeManagerService;

    /**
     * @Method: SendCode
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:手机验证码验证
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/19 14:09
     * @Param: * @param params
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/sms/sendCode",method = RequestMethod.POST)
    @ResponseBody
    public Result SendCode(@RequestBody String []params){
        String phone = params[4];
        int code = GetRandomCode(0,999999);
        logger.info("获得参数信息为："+phone+"\t"+code);
        int result = this.sendCodeManagerService.SendCode(phone,code);
        logger.info(""+result);
        if(result == 0){
            return  Result.failure(ResultCode.FAILURE);
        }
        return Result.success(ResultCode.SUCCESS);
    }
    /**
     * @Method: GetRandomCode
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:生产随机数
     * @Return: int
     * @Exception:
     * @Date: 2021/2/19 12:39
     * @Param: * @param min
     * @param max
     * @Return: int
     */
    public static int GetRandomCode(int min,int max){

        int randomNum = min +(int)(Math.random()*(max-min)+1);
        return  randomNum;
    }

}


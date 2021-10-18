package com.zyt.controller.register.backDeskRegister;

import com.zyt.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.register.backDeskRegister
 * @ClassName: UserInfoManagerController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户管理接口层
 * @Date: 18:11 2021/5/29
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/userInfoManager")
public class BackUserInfoManagerController {

    private Logger logger = LoggerFactory.getLogger(BackUserInfoManagerController.class);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 保存后台用户注册信息
      * @Return:
      * @Exception:
      * @Date: 2021/5/29 18:16
      * @Param: * @param null
      * @Return:
      */
    @RequestMapping(value = "/addRegisterInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result addRegisterInfo(@RequestBody String [] infos){
        for (int i = 0; i < infos.length; i++) {
            logger.info("获得的注册信息为:"+infos[i]);
        }
        return  null;
    }
}

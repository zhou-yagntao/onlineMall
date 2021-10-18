package com.zyt.controller.login.backDeskLogin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.SystemUser;
import com.zyt.entity.vo.LoginSuccessVo;
import com.zyt.entity.vo.SystemMenuVo;
import com.zyt.service.systemUserLogin.BackSystemUserILoginService;
import com.zyt.service.systemUserLogin.SystemUserLoginService;

import com.zyt.utils.ChangeJsonTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.login.backDeskLogin
 * @ClassName: UserController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 普通用户登录控制层
 * @Date: 13:31 2021/3/16
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user")

public class UserLoginController {

    private Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private BackSystemUserILoginService backSystemUserILoginService;


    /**
     * @Method: ToLogin
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:默认访问页面
     * @Return: void
     * @Exception:
     * @Date: 2021/3/16 20:07
     * @Param: * @param response
     * @Return: void
     */
    @RequestMapping(value = "/toLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result ToLogin(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        HttpServletResponse response) throws IOException {
        logger.info("获得的登录信息为:"+username+"\t"+password);
        LoginSuccessVo loginSuccessVo = this.backSystemUserILoginService.checkIsExistsOfCurrUserByUserName(username);
        logger.info("--->"+loginSuccessVo.toString());
        return Result.success(ResultCode.SUCCESS,loginSuccessVo.getRole());
    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:用户登录  默认访问页面
      * @Return:
      * @Exception:
      * @Date: 2021/3/29 13:40
      * @Param: * @param null
      * @Return:
      */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result Login(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        logger.info("得到结果了。。。。。。");
        return Result.success(ResultCode.SUCCESS);
    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得用户授权信息并获得该权限下的菜单栏信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/29 13:41
      * @Param: * @param null
      * @Return:
      */
    @RequestMapping(value="/login/menu/info",method = RequestMethod.POST)
    @ResponseBody
    public Result LoginInfo(@RequestParam(value = "role",required = true)String role){
        logger.info("获得的角色为:"+role.substring(2,role.length()-2));
        String roleName = role.substring(2,role.length()-2);
        List<SystemMenuVo> systemMeunInfoOfCurrUserRole = this.backSystemUserILoginService.getSystemMeunInfoOfCurrUserRole(roleName);
        //SelfUserEntity userDetails = (SelfUserEntity) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        logger.info("-----------------------");
 //       logger.info(""+userDetails);
//       List<SystemMenuVo> systemMeunInfos = this.systemUserLoginService.getSystemMeunInfoOfCurrUserRole(userDetails);
//        logger.info("获得的菜单栏信息为:"+systemMeunInfos);
        logger.info("菜单封装后信息为:"+systemMeunInfoOfCurrUserRole);
        if (systemMeunInfoOfCurrUserRole == null && systemMeunInfoOfCurrUserRole.size() == 0){
            return  Result.failure(ResultCode.USER_NOT_AUTHOR);
        }
        return Result.success(ResultCode.PERMISSION_AUTO_SUCCESS,systemMeunInfoOfCurrUserRole);
    }

}

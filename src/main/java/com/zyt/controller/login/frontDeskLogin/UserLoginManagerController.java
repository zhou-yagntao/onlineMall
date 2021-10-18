package com.zyt.controller.login.frontDeskLogin;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.userService.UserLoginManagerService;
import com.zyt.utils.VerificationCodeImgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: GetUserLoginVerifyCodeController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 获得登录验证码
 * @Date: 13:28 2021/2/14
 * @Version: 1.0
 */
@Controller
@RequestMapping("/userLoginManager")
public class UserLoginManagerController {

    private Logger logger = LoggerFactory.getLogger(UserLoginManagerController.class);

    //定义验证图片存储信息
    private static  String verifyCodeInfo = null;

    @Autowired
    private UserLoginManagerService userLoginManagerService;

    /**
     * @Method: GetUserLoginVerifyCode
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得验证码
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/14 13:35
     * @Param: * @param
     * @Return: com.zyt.entity.Result
     */
    @PostMapping(value ="/getUserLoginVerifyCode")
    @ResponseBody
    public Result GetUserLoginVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerificationCodeImgUtil verificationCodeImgUtil  = new VerificationCodeImgUtil(160,40,5,50);
        verifyCodeInfo = verificationCodeImgUtil.getCode();
        String path = "D:\\workplace\\vscode_workplace\\graduationDesignMaterial\\reception_onlinedrinksmall\\src\\assets\\images\\"+"verityCode";
        verificationCodeImgUtil.write(path+".jpg");
        return  null;
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public Result UserLogin(@RequestBody String [] loginInfo){
        int result = this.userLoginManagerService.GetUserLoginResult(loginInfo,verifyCodeInfo);
        //若返回结果为1则表示验证码输入不正确 进行返回
        if (result == 1){
            logger.info("控制层接收返回参数:"+result);
           return  Result.failure(ResultCode.PARAM_NO_ACCESS);
        }
        //若返回结果为2则表示输入的密码不合法则返回对应的状态码进行提示
        else if(result == 2){
            return  Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        //若返回结果为3则表示用户正确登录 跳转到登录成功的页面
        else if(result == 3){
             return  Result.success(ResultCode.SUCCESS);
        }
        return  Result.success(ResultCode.SUCCESS,loginInfo[0]);
    }

}

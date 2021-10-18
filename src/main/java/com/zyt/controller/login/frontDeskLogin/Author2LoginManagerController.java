package com.zyt.controller.login.frontDeskLogin;

import com.zyt.constant.AutoServerConstant;
import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.vo.UserFinishLoginResult;
import com.zyt.service.thirdSupport.WeiBoLoginManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.login.frontDeskLogin
 * @ClassName: Author2LoginManagerController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 第三方登录，单点登录管理控制层模块
 * @Date: 20:52 2021/2/18
 * @Version: 1.0
 */
@Controller
@RequestMapping("/oauth2")
public class Author2LoginManagerController {
    private Logger logger = LoggerFactory.getLogger(Author2LoginManagerController.class);


    @Autowired
    private WeiBoLoginManagerService weiBoLoginManagerService;

    UserFinishLoginResult userFinishLoginResult  = null;

    private HttpSession sessions = null;

    /**
     * @Method: WeiBo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:微博登录控制层
     * @Return: java.lang.String
     * @Exception:
     * @Date: 2021/2/22 11:26
     * @Param: * @param code
     * @Return: java.lang.String
     */
    @GetMapping("/weibo/success")
    @ResponseBody
    public void WeiBo(@RequestParam(value = "code",required = false)String code,
                      HttpServletResponse response,
                      HttpSession session
                      ) throws IOException, ServletException {

        userFinishLoginResult = this.weiBoLoginManagerService.WeiBoAuthorize(code);
        logger.info("返回的结果为:"+userFinishLoginResult.toString());
        //若返回的封装结果为空则返回登录界面
        if(userFinishLoginResult == null){
            String url= "http://127.0.0.1:9999/login";
            response.sendRedirect(url);
        }else{
            //若返回的封装结果不为空则返回登录界面
            //return "redirect:http://127.0.0.1:9999/FinishLoginHome";
            //第一次使用session,浏览器保存卡号  以后浏览器访问都会带上这个卡号
            session.setAttribute(AutoServerConstant.USER_LOGIN_SUCCESS,userFinishLoginResult);
            sessions = session;
            String url = "http://127.0.0.1:9999/MallHome";
            response.sendRedirect(url);
            //return  "redirect:http://127.0.0.1:9999/FinishLoginHome";
        }
    }

    @RequestMapping(value = "/getUserLoginResult",method = RequestMethod.POST)
    @ResponseBody
    public Result getUserLoginResult(){
        logger.info("用户登录信息为:"+sessions.getAttribute(AutoServerConstant.USER_LOGIN_SUCCESS));
        return  Result.success(ResultCode.SUCCESS,userFinishLoginResult);
    }

}

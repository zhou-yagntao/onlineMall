package com.zyt.handle;

//import com.zyt.component.TokenComponent;
//import com.zyt.constant.UserLoginTokenConstant;
//import com.zyt.entity.Result;
//import com.zyt.entity.ResultCode;
//import com.zyt.entity.security.SelfUserEntity;
//import com.zyt.utils.SecurityResponseResultUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @ProjectName: online_drink_mall
// * @Package: com.zyt.handle
// * @ClassName: UserLoginSuccessHandler
// * @Author: zhou_yangtao@yeah.net
// * @Description: 用户登录处理类
// * @Date: 22:51 2021/3/15
// * @Version: 1.0
// */
//@Component
//public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
//
//    private Logger logger = LoggerFactory.getLogger(UserLoginSuccessHandler.class);
//
//    @Autowired
//    private TokenComponent tokenComponent;
//
//    /**
//     * @Method: onAuthenticationSuccess
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:用户登录成功返回结果
//     * @Return: void
//     * @Exception:
//     * @Date: 2021/3/15 22:52
//     * @Param: * @param httpServletRequest
//     * @param httpServletResponse
//     * @param authentication
//     * @Return: void
//     */
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        // 组装JWT
//        logger.info("--"+authentication.getPrincipal().toString());
//        //selfUserEntity.setUsername(authentication.getPrincipal().toString());
//        SelfUserEntity selfUserEntity = (SelfUserEntity)authentication.getPrincipal();
//        logger.info("准备封装的信息为:"+selfUserEntity);
//        String token = this.tokenComponent.createTokenOfCurrUser(selfUserEntity);
//        token = UserLoginTokenConstant.USER_LOGIN_TOKEN_PREFIX+token;
//        // 封装返回参数
//        Map<String,Object> resultData = new HashMap<>();
//        resultData.put("msg","登录成功");
//        resultData.put("token",token);
//        Result.success(ResultCode.SUCCESS,resultData);
//        SecurityResponseResultUtil.responseJson(httpServletResponse,resultData);
//    }
//}

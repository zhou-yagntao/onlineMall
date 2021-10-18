package com.zyt.handle;

//import com.zyt.entity.Result;
//import com.zyt.entity.ResultCode;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @ProjectName: online_drink_mall
// * @Package: com.zyt.handle
// * @ClassName: UserLoginFailureHandler
// * @Author: zhou_yangtao@yeah.net
// * @Description: 用户登录失败处理类
// * @Date: 22:58 2021/3/15
// * @Version: 1.0
// */
//@Component
//public class UserLoginFailureHandler  implements AuthenticationFailureHandler {
//
//    private Logger logger = LoggerFactory.getLogger(UserLoginFailureHandler.class);
//
//    /**
//     * @Method: onAuthenticationFailure
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:返回不同类型的登录失败的原因
//     * @Return: void
//     * @Exception:
//     * @Date: 2021/3/15 23:03
//     * @Param: * @param httpServletRequest
//     * @param httpServletResponse
//     * @param exception
//     * @Return: void
//     */
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
//        //封装不同类型登录失败原因
//        if (exception instanceof UsernameNotFoundException){
//            Result.failure(ResultCode.USER_NOT_EXIST);
//         }
//        if (exception instanceof LockedException){
//            Result.failure(ResultCode.USER_ACCOUNT_FORBIDDEN);
//        }
//        if (exception instanceof BadCredentialsException){
//            Result.failure(ResultCode.USER_LOGIN_ERROR);
//        }
//        Result.failure(ResultCode.FAILURE);
//
//    }
//}

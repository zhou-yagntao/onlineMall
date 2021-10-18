package com.zyt.handle;

//import com.zyt.entity.Result;
//import com.zyt.entity.ResultCode;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.handle
 * @ClassName: UserAuthenticationEntryPointHandler
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户未登录处理类
 * @Date: 22:47 2021/3/15
 * @Version: 1.0
 */
//@Component
//public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
//
//    /**
//     * @Method: commence
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:处理用户未登录执行结果
//     * @Return: void
//     * @Exception:
//     * @Date: 2021/3/15 22:48
//     * @Param: * @param httpServletRequest
//     * @param httpServletResponse
//     * @param e
//     * @Return: void
//     */
//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        Result.failure(ResultCode.USER_NOT_LOGGED_IN,"用户未登录");
//    }
//}

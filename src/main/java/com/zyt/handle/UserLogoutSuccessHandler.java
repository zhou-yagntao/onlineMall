package com.zyt.handle;

//import com.zyt.entity.Result;
//import com.zyt.entity.ResultCode;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
// * @ClassName: UserLogoutSuccessHandler
// * @Author: zhou_yangtao@yeah.net
// * @Description: 用户退出处理类
// * @Date: 23:04 2021/3/15
// * @Version: 1.0
// */
//@Component
//public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
//
//    /**
//     * @Method: onLogoutSuccess
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:处理退出业务
//     * @Return: void
//     * @Exception:
//     * @Date: 2021/3/16 10:23
//     * @Param: * @param httpServletRequest
//     * @param httpServletResponse
//     * @param authentication
//     * @Return: void
//     */
//    @Override
//    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        SecurityContextHolder.clearContext();
//        Result.success(ResultCode.SUCCESS,"退出成功");
//
//    }
//}

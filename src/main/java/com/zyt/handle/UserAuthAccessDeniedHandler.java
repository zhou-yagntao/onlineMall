package com.zyt.handle;

//import com.zyt.entity.Result;
//import com.zyt.entity.ResultCode;
//import com.zyt.utils.SecurityResponseResultUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
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
// * @ClassName: UserAuthAccessDeniedHandler
// * @Author: zhou_yangtao@yeah.net
// * @Description: 用户无权限访问处理
// * @Date: 10:30 2021/3/16
// * @Version: 1.0
// */
//@Component
//public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
//
//    private Logger logger = LoggerFactory.getLogger(UserAuthenticationEntryPointHandler.class);
//
//    /**
//     * @Method: handle
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:当前用户未授权执行业务
//     * @Return: void
//     * @Exception:
//     * @Date: 2021/3/16 10:33
//     * @Param: * @param httpServletRequest
//     * @param httpServletResponse
//     * @param e
//     * @Return: void
//     */
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        Map<String,Object> map = new HashMap<>();
//        Result.failure(ResultCode.USER_NOT_AUTHOR);
//        map.put(ResultCode.USER_NOT_AUTHOR.toString(),"未授权");
//        SecurityResponseResultUtil.responseJson(httpServletResponse,map);
//    }
//}

package com.zyt.interceptor;

import com.zyt.aop.CartInterceptorAop;
import com.zyt.constant.AutoServerConstant;
import com.zyt.constant.CartConstant;
import com.zyt.entity.vo.EncapsulateUserInfoTo;
import com.zyt.entity.vo.UserFinishLoginResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.interceptor
 * @ClassName: CartInterceptor
 * @Author: zhou_yangtao@yeah.net
 * @Description: 首次进入购物车拦截器
 * @Date: 20:38 2021/2/22
 * @Version: 1.0
 */

public class CartInterceptor implements HandlerInterceptor {

    private Logger logger  = LoggerFactory.getLogger(CartInterceptor.class);

    public  static  ThreadLocal<EncapsulateUserInfoTo> threadLocal = new ThreadLocal<>();

    /**
     * @Method: preHandle
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:进入购物车之前处理逻辑
     * @Return: boolean
     * @Exception:
     * @Date: 2021/2/23 10:20
     * @Param: * @param request
     * @param response
     * @param handler
     * @Return: boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进来了");
        HttpSession session = request.getSession();
        logger.info("获得的session结果为:"+session.getAttribute(AutoServerConstant.USER_LOGIN_SUCCESS));
        UserFinishLoginResult userFinishLoginResult  = (UserFinishLoginResult) session.getAttribute(AutoServerConstant.USER_LOGIN_SUCCESS);
        logger.info("结果为:"+userFinishLoginResult.toString());
        EncapsulateUserInfoTo encapsulateUserInfoTo = new EncapsulateUserInfoTo();
        if (userFinishLoginResult != null){
            //表示用户登录
            encapsulateUserInfoTo.setUserId(userFinishLoginResult.getUid());
        }
        //获取页面的cookie信息
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            //通过for循环遍历
            for(Cookie cookie : cookies){
                String cookieName = cookie.getName();
                //若获取的名称与我们定义的名字相同
                if (cookieName.equals(CartConstant.TEMP_USER_COOKIE_NAME)){
                    //封装对应的值
                    encapsulateUserInfoTo.setUserKey(cookie.getValue());
                    encapsulateUserInfoTo.setTempUser(true);
                }
            }
        }
        //若用户的第一次使用 也没有注册 则我哦们就需要为其分配一个用户键
        if(StringUtils.isEmpty(encapsulateUserInfoTo.getUserKey())){
            String uuid = UUID.randomUUID().toString();
            encapsulateUserInfoTo.setUserKey(uuid);

        }
        threadLocal.set(encapsulateUserInfoTo);
        return  true;
    }

    /**
     * @Method: postHandle
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:业务执行之后设置过期时间相关信息
     * @Return: void
     * @Exception:
     * @Date: 2021/2/23 10:17
     * @Param: * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @Return: void
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //没有临时用户则进行设置过期时间
        if (!threadLocal.get().isTempUser()){
            Cookie cookie = new Cookie(CartConstant.TEMP_USER_COOKIE_NAME,threadLocal.get().getUserKey());
            cookie.setDomain("127.0.0.1");
            cookie.setMaxAge(CartConstant.TEMP_USER_COOKIE_TIMEOUT);
            response.addCookie(cookie);
        }

    }
}

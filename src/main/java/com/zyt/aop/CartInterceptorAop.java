package com.zyt.aop;

import com.zyt.constant.AutoServerConstant;
import com.zyt.constant.CartConstant;
import com.zyt.entity.vo.EncapsulateUserInfoTo;
import com.zyt.entity.vo.UserFinishLoginResult;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
 * @Description: 自定义购物车拦截切面配置类
 * @Date: 18:40 2021/2/22
 * @Version: 1.0
 *
 */
@Aspect
@Component
public class CartInterceptorAop {

//    private Logger logger  = LoggerFactory.getLogger(CartInterceptorAop.class);
//
//    public  static  ThreadLocal<EncapsulateUserInfoTo> threadLocal = new ThreadLocal<>();
//
//    @Pointcut("execution(public * com.zyt.controller.cartMoudle.CartManagerController.CartListPage())")
//    public  void  cartInterceptorPointCut(){
//
//    }

//    @Before(value = "cartInterceptorPointCut()")
//    public boolean beforeHandleCartInterceptorPointCut(){
//        logger.info("进来了");
//        HttpServletRequest request = null;
//        HttpSession session = request.getSession();
//        UserFinishLoginResult userFinishLoginResult  = (UserFinishLoginResult) session.getAttribute(AutoServerConstant.USER_LOGIN_SUCCESS);
//        logger.info("结果为:"+userFinishLoginResult);
//        EncapsulateUserInfoTo encapsulateUserInfoTo = new EncapsulateUserInfoTo();
//        if (userFinishLoginResult != null){
//            //表示用户登录
//            encapsulateUserInfoTo.setUserId(userFinishLoginResult.getUid());
//        }
//        //获取页面的cookie信息
//        Cookie [] cookies = request.getCookies();
//        if(cookies != null && cookies.length > 0) {
//            //通过for循环遍历
//            for(Cookie cookie : cookies){
//                 String cookieName = cookie.getName();
//                 //若获取的名称与我们定义的名字相同
//                 if (cookieName.equals(CartConstant.TEMP_USER_COOKIE_NAME)){
//                     //封装对应的值
//                     encapsulateUserInfoTo.setUserKey(cookie.getValue());
//                     encapsulateUserInfoTo.setTempUser(true);
//                 }
//            }
//        }
//        //若用户的第一次使用 也没有注册 则我哦们就需要为其分配一个用户键
//        if(StringUtils.isEmpty(encapsulateUserInfoTo.getUserKey())){
//            String uuid = UUID.randomUUID().toString();
//            encapsulateUserInfoTo.setUserKey(uuid);
//
//        }
//        threadLocal.set(encapsulateUserInfoTo);
//        return  true;
//    }
//
//    @After(value = "cartInterceptorPointCut()")
//    public boolean afterHandleCartInterceptorPointCut(){
//        HttpServletResponse response = null;
//        //没有临时用户则进行设置过期时间
//        if (!threadLocal.get().isTempUser()){
//            Cookie cookie = new Cookie(CartConstant.TEMP_USER_COOKIE_NAME,threadLocal.get().getUserKey());
//            cookie.setDomain("127.0.0.1");
//            response.addCookie(cookie);
//        }
//        return  true;

//    }
}

//package com.zyt.utils;
//
//import com.zyt.entity.vo.SecurityUserVo;
//import org.springframework.security.core.context.SecurityContextHolder;
//
///**
// * Security工具类
// * @Author Sans
// * @CreateTime 2019/10/2 13:16
// */
//public class SecurityUtil {
//
//    /**
//     * 私有化构造器
//     */
//    private SecurityUtil(){}
//
//    /**
//     * 获取当前用户信息
//     */
//    public static SecurityUserVo getUserInfo(){
//        SecurityUserVo securityUserVo = (SecurityUserVo) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
//        return securityUserVo;
//    }
//
//
//    /**
//     * 获取当前用户账号
//     */
//    public static String getUserName(){
//        return getUserInfo().getUsername();
//    }
//}
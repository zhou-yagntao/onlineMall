package com.zyt.component;

import org.apache.http.HttpStatus;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.component
 * @ClassName: CorsFilter
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 22:19 2021/3/30
 * @Version: 1.0
 */
//@Component
//@WebFilter(filterName = "requestFilter", urlPatterns = {"/*"})
//public class Cors implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        // 指定允许其他域名访问
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        // 响应类型
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
//        // 响应头设置
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");
//        if ("OPTIONS".equals(request.getMethod())) {
//            response.setStatus(HttpStatus.SC_NO_CONTENT);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}

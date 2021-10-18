package com.zyt.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: springboot_mybatis_druid_pagehelper_logf4j
 * @Package: com.chinaedu.config
 * @ClassName: DruidFilterConfig
 * @Author: Justin
 * @Description: 监控
 * @Date: 11:55 2020/5/18
 * @Version: 1.0
 */
@Configuration
public class DruidFilterConfig {

        @Value("${spring.datasources.druidLoginName}")
        private String druidLoginName;
        @Value("${spring.datasources.druidPassword}")
        private String druidPassword;

         @Bean
         public ServletRegistrationBean druidServlet() {
            ServletRegistrationBean reg = new ServletRegistrationBean();
            reg.setServlet(new StatViewServlet());
            /** url 匹配 */
            reg.addUrlMappings("/druid/*");
            /** IP白名单 (没有配置或者为空，则允许所有访问) */
            reg.addInitParameter("allow", "1**.2**.1**.3*,127.0.0.1");
            /** IP黑名单 (存在共同时，deny优先于allow) */
            reg.addInitParameter("deny", "1**.2**.1**.3*");
            /** 登录名  */
            reg.addInitParameter("loginUsername", this.druidLoginName);
            /**  登录密码 */
            reg.addInitParameter("loginPassword", this.druidPassword);
            /**  禁用HTML页面上的“Reset All”重置所有功能 */
            reg.addInitParameter("resetEnable", "false");
            return reg;
        }

        @Bean(name="druidWebStatFilter")
        public FilterRegistrationBean filterRegistrationBean() {
            FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
            filterRegistrationBean.setFilter(new WebStatFilter());
            filterRegistrationBean.addUrlPatterns("/*");
            filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); //忽略资源
            filterRegistrationBean.addInitParameter("profileEnable", "true");
            filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
            filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
            return filterRegistrationBean;
        }

}

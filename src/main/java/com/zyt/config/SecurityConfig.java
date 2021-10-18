package com.zyt.config;

//import com.zyt.component.TokenComponent;
//import com.zyt.component.UserAuthenticationProvider;
//import com.zyt.component.UserPermissionConponent;
//import com.zyt.constant.UserLoginTokenConstant;
//import com.zyt.filter.UserLoginTokenFilter;
//import com.zyt.handle.*;
//import com.zyt.utils.Md5Utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.sql.DataSource;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: SecurityConfig
 * @Author: zhou_yangtao@yeah.net
 * @Description: security权限管理模块配置中心
 * @Date: 17:28 2021/3/15
 * @Version: 1.0
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled =  true)

//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;
//
//    @Autowired
//    private UserLoginSuccessHandler userLoginSuccessHandler;
//
//    @Autowired
//    private UserLoginFailureHandler userLoginFailureHandler;
//
//    @Autowired
//    private UserLogoutSuccessHandler logoutSuccessHandler;
//
//    @Autowired
//    private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private TokenComponent tokenComponent;
//
//
//    @Autowired
//    private UserAuthenticationProvider userAuthenticationProvider;
//
//     /**
//     * @Method: bCryptPasswordEncoder
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:进行密码加密
//     * @Return: org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//     * @Exception:
//     * @Date: 2021/3/16 16:08
//     * @Param: * @param
//     * @Return: org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//     */
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * @Method: configure
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:  配置登录验证逻辑
//     * @Return: void
//     * @Exception:
//     * @Date: 2021/3/16 12:35
//     * @Param: * @param authenticationManagerBuilder
//     * @Return: void
//     */
//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
//          authenticationManagerBuilder.authenticationProvider(userAuthenticationProvider);
//    }
//
//    @Bean
//    public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler(){
//        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
//        defaultWebSecurityExpressionHandler.setPermissionEvaluator(new UserPermissionConponent());
//        return defaultWebSecurityExpressionHandler;
//    }
//
//
//    /**
//     * @Method: configure
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:编写核心配置类
//     * @Return: void
//     * @Exception:
//     * @Date: 2021/3/15 21:48
//     * @Param: * @param security
//     * @Return: void
//     */
//    @Override
//    public void  configure(HttpSecurity security) throws Exception {
//           //开启跨域
//           security.cors()
//                   .and();
//           //关闭csrf防护
//           security.csrf()
//                   .disable()
//                   .headers()
//                   .frameOptions()
//                   .disable()
//                   .and();
//           security.authorizeRequests()
//                   //不进行权限验证的请求资源
//                   .antMatchers(UserLoginTokenConstant.USER_LOGIN_NOT_ANTMATCHERS.split(","))
//                   .permitAll()
//                   //其他资源需要进行登录才能访问
//                   .anyRequest()
//                   .authenticated()
//                   .and();
//           //配置未登录自定义处理类
//           security.httpBasic()
//                   .authenticationEntryPoint(userAuthenticationEntryPointHandler)
//                   .and();
//           //配置登录地址
//           security.formLogin()
//                   .loginProcessingUrl("/user/login/toLogin")
//                    //配置登录才能够自定义处理类
//                   .successHandler(userLoginSuccessHandler)
//                   .failureHandler(userLoginFailureHandler)
//                   .and();
//           //配置登出地址
//           security.logout()
//                   .logoutUrl("/user/loginOut")
//                   .logoutSuccessHandler(logoutSuccessHandler)
//                   .and();
//           //处理没有权限自定义处理类
//           security.exceptionHandling()
//                   .accessDeniedHandler(userAuthAccessDeniedHandler)
//                   .and();
//           //基于token 不需要session
//           security.sessionManagement()
//                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//           //禁用缓存
//           security.headers()
//                   .cacheControl()
//                   .and();
//           //开启记住我功能
//           security.rememberMe()
//                   //七天免登陆
//                   .tokenValiditySeconds(604800)
//                   .tokenRepository(persistentTokenRepository())
//                   .and();
//           //添加过滤器
//           security.addFilter(new UserLoginAuthorFilter(authenticationManager()));
//    }
//
//
//
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository(){
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        return jdbcTokenRepository;
//    }
//}

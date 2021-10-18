package com.zyt.filter;

//import com.zyt.component.TokenComponent;
//import com.zyt.constant.UserLoginTokenConstant;
//import com.zyt.entity.security.SelfUserEntity;
//import com.zyt.entity.vo.SecurityUserVo;
//
//import org.codehaus.jackson.map.ObjectMapper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.servlet.FilterChain;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.filter
 * @ClassName: UserLoginTokenFilter
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户登录认证过滤器
 * @Date: 20:20 2021/3/15
 * @Version: 1.0
 */
//public class UserLoginTokenFilter extends UsernamePasswordAuthenticationFilter {

//     @Autowired
//     private TokenComponent tokenComponent;
//
//     @Autowired
//     private RedisTemplate redisTemplate;
//
//     @Autowired
//     private AuthenticationManager authenticationManager;
//
//
//     public UserLoginTokenFilter(AuthenticationManager authenticationManager,TokenComponent tokenComponent,RedisTemplate redisTemplate){
//        this.authenticationManager = authenticationManager;
//        this.tokenComponent = tokenComponent;
//        this.redisTemplate = redisTemplate;
//        this.setPostOnly(true); //只允许post方式提交
//        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login","POST"));
//     }
//
//
//    /**
//     * @Method: attemptAuthentication
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:获取用户登录的用户名 token
//     * @Return: org.springframework.security.core.Authentication
//     * @Exception:
//     * @Date: 2021/3/15 20:22
//     * @Param: * @param request
//     * @param response
//     * @Return: org.springframework.security.core.Authentication
//     */
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
//        //获取表单提交数据
//        SelfUserEntity selfUserEntity = null;
//        try {
//            selfUserEntity = new  ObjectMapper().readValue(request.getInputStream(),SelfUserEntity.class);
//            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(selfUserEntity.getUsername(),selfUserEntity.getPassword(),new ArrayList<>()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return  null;
//    }
//
//    /**
//     * @Method: successfulAuthentication
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:
//     * @Return: void
//     * @Exception:成功验证执行的方法
//     * @Date: 2021/3/15 20:23
//     * @Param: * @param request
//     * @param response
//     * @param filterChain
//     * @param authentication
//     * @Return: void
//     */
//    @Override
//    public void successfulAuthentication(HttpServletRequest request,
//                                         HttpServletResponse response,
//                                         FilterChain filterChain,
//                                         Authentication authentication)
//                                         throws AuthenticationException{
//        //认证成功获得认证成功信息 得到认证成功用户信息
//        SelfUserEntity selfUserEntity = (SelfUserEntity) authentication.getPrincipal();
//        //根据用户名生成token
//        String token = tokenComponent.createTokenOfCurrUser(selfUserEntity);
//        //将用户名称和权限放入到数据库中
//        this.redisTemplate.opsForValue().set(selfUserEntity.getUsername(),selfUserEntity.getAuthorities());
//        response.setHeader("token", UserLoginTokenConstant.USER_LOGIN_TOKEN_PREFIX +token);
//    }
//
//    /**
//     * @Method: unsuccessfulAuthentication
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:
//     * @Return: void
//     * @Exception:登录失败
//     * @Date: 2021/3/15 20:24
//     * @Param: * @param request
//     * @param response
//     * @param failed
//     * @Return: void
//     */
//    @Override
//    public void  unsuccessfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            AuthenticationException failed)
//                                            throws AuthenticationException{
//        try {
//            response.getWriter().write("authentication failed, reason: " + failed.getMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

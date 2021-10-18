package com.zyt.filter;



import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.filter
 * @ClassName: UserLoginAuthorFilter
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户登录授权过滤器
 * @Date: 21:12 2021/3/15
 * @Version: 1.0
 */
//public class UserLoginAuthorFilter extends BasicAuthenticationFilter {
//
//
//    public UserLoginAuthorFilter(AuthenticationManager authenticationManager){
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain) throws IOException, ServletException {
//      //获取请求头中的token
//      String token_head = request.getHeader(UserLoginTokenConstant.USER_LOGIN_TOKEN_HEADER);
//      //判断如果没有权限 则放行
//      if (null != token_head  && token_head.startsWith(UserLoginTokenConstant.USER_LOGIN_TOKEN_PREFIX)){
//          getAuthentication(token_head);
//      }
//      chain.doFilter(request,response);
//      return;
//    }
//
//    private void getAuthentication(String token_head) {
//        try {
//            String token = token_head.replace(UserLoginTokenConstant.USER_LOGIN_TOKEN_PREFIX,"");
//            if (!StringUtils.isEmpty(token)){
//                //解析jwt
//                Claims claims = getClaimsFromToken(token);
//                logger.info("获得的信息为:"+claims);
//                String userName = claims.getSubject();
//                String userId = claims.getId();
//                logger.info("获得的用户名信息为:"+userName+"\t"+userId);
//                if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(userId)){
//                    Set<GrantedAuthority> authorities = new HashSet<>();
//                    String authority = claims.get("authorities").toString();
//                    System.out.println(authority);
//                    if (!StringUtils.isEmpty(authority)){
//                        List<Map<String,String>> authorityMap = JSON.parseObject(authority,List.class);
//                        for (Map<String,String> role : authorityMap){
//                            if (!StringUtils.isEmpty(String.valueOf(role))){
//                                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
//                            }
//                            System.out.println(authorities);
//                        }
//                    }
//                    logger.info("---->"+authorities);
//                    SelfUserEntity selfUserEntity  = new SelfUserEntity();
//                    selfUserEntity.setUserId(Long.parseLong(claims.getId()));
//                    selfUserEntity.setUsername(claims.getSubject());
//                    selfUserEntity.setAuthorities(authorities);
//                    logger.info("获得的authorities为:"+selfUserEntity.toString());
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(selfUserEntity,userId,authorities);
//                    logger.info("组装信息为："+authenticationToken);
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            }
//        }catch (ExpiredJwtException e){
//              new TokenExpiredException();
//        }
//    }
//
//    //解析token
//    private static Claims getClaimsFromToken(String token) {
//        Claims claims = Jwts.parser()
//                            .setSigningKey(UserLoginTokenConstant.USER_LOGIN_TOKEN_SECRET_KEY)
//                            .parseClaimsJws(token)
//                            .getBody();
//        if (null == claims){
//            return  null;
//        }
//        return  claims;
//    }
//}

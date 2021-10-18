package com.zyt.component;

//import com.zyt.entity.SystemUser;
//import com.zyt.entity.security.SelfUserEntity;
//import com.zyt.entity.vo.SecurityUserVo;
//import com.zyt.exception.BadCredentialsException;
//import com.zyt.exception.UsernameNotFoundException;
//import com.zyt.service.systemUserLogin.SystemUserLoginService;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import java.util.*;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.component
 * @ClassName: UserAuthenticationProvider
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户登录验证逻辑
 * @Date: 12:37 2021/3/16
 * @Version: 1.0
 */
//@Component
//public class UserAuthenticationProvider implements AuthenticationProvider {
//
//    private Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);
//
//
//    @Autowired
//    private SystemUserLoginService systemUserLoginService;
//
//    /**
//     * @Method: authenticate
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description: 自定义登录验证
//     * @Return: org.springframework.security.core.Authentication
//     * @Exception:
//     * @Date: 2021/3/16 13:12
//     * @Param: * @param authentication
//     * @Return: org.springframework.security.core.Authentication
//     */
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        logger.info("========================>"+authentication+"<=====================================");
//        //获得页面传递的用户名 密码
//        String username = (String)authentication.getPrincipal();
//        String password = (String)authentication.getCredentials();
//        //查询当前用户是否存在
//        SelfUserEntity selfUserEntity = this.systemUserLoginService.checkIsExistsOfCurrUserByUserName(username);
//        //用户名不存在
//        if (selfUserEntity == null){
//            throw  new UsernameNotFoundException();
//        }
//        //密码不匹配
//        if (!new BCryptPasswordEncoder().matches(password,selfUserEntity.getPassword())){
//            throw new BadCredentialsException();
//        }
//        if (selfUserEntity.getStatus().equals("PROHIBIT")){
//            throw new LockedException("该用户已被冻结");
//        }
//        //根据id获得当前用户的权限列表
//        List<String> role_permissions = this.systemUserLoginService.getUserRolePermissionOfCurrUserByUserId(selfUserEntity.getUserId().intValue());
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        for(String permission : role_permissions){
//            SimpleGrantedAuthority authority  = new SimpleGrantedAuthority(permission);
//            authorities.add(authority);
//        }
//        selfUserEntity.setAuthorities(authorities);
//        logger.info("封装用户授权后的信息为:"+selfUserEntity.toString());
//        return new UsernamePasswordAuthenticationToken(selfUserEntity,password,authorities);
//     }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}

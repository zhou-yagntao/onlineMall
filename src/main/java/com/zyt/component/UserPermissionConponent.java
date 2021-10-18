package com.zyt.component;

//import com.zyt.entity.security.SelfUserEntity;
//import com.zyt.service.systemUserLogin.SystemUserLoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.component
 * @ClassName: UserPermissionConponent
 * @Author: zhou_yangtao@yeah.net
 * @Description: 自定义用户权限组件
 * @Date: 12:42 2021/3/18
 * @Version: 1.0
 */
//@Component
//public class UserPermissionConponent implements PermissionEvaluator {
//
//    @Autowired
//    private SystemUserLoginService systemUserLoginService;
//
//
//    @Override
//    public boolean hasPermission(Authentication authentication,
//                                 Object url,
//                                 Object permission) {
//        SelfUserEntity selfUserEntity = (SelfUserEntity) authentication.getPrincipal();
//        Set<String> permissions = new HashSet<>();
//        List<String> pemissionLists = this.systemUserLoginService.getUserRolePermissionOfCurrUserByUserId(selfUserEntity.getUserId().intValue());
//        for(String permmission : pemissionLists){
//            permissions.add(permmission);
//        }
//        if (permissions.contains(permission)){
//            return  true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
//        return false;
//    }
//}

//package com.zyt.entity.vo;
//
//import com.zyt.entity.SystemUser;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * @ProjectName: online_drink_mall
// * @Package: com.zyt.entity.vo
// * @ClassName: SecurityUserVo
// * @Author: zhou_yangtao@yeah.net
// * @Description: 从security获得的对象数据
// * @Date: 20:53 2021/3/15
// * @Version: 1.0
// */
//public class SecurityUserVo implements UserDetails {
//
//    //当前登录用户
//    private transient SystemUser currentUserInfo;
//
//    private List<String> permissionList;
//
//    public SecurityUserVo(){
//        super();
//    }
//
//    public SecurityUserVo(SystemUser systemUser){
//        if(systemUser != null){
//            this.currentUserInfo = systemUser;
//        }
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        for(String permission : permissionList){
//            if (StringUtils.isEmpty(permission)){continue;}
//            SimpleGrantedAuthority authority  = new SimpleGrantedAuthority(permission);
//            authorities.add(authority);
//        }
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public SystemUser getCurrentUserInfo() {
//        return currentUserInfo;
//    }
//
//    public void setCurrentUserInfo(SystemUser currentUserInfo) {
//        this.currentUserInfo = currentUserInfo;
//    }
//
//    public List<String> getPermissionList() {
//        return permissionList;
//    }
//
//    public void setPermissionList(List<String> permissionList) {
//        this.permissionList = permissionList;
//    }
//}

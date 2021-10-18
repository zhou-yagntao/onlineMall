package com.zyt.entity.security;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.io.Serializable;
//import java.util.Collection;
//
///**
// * @ProjectName: online_drink_mall
// * @Package: com.zyt.entity.security
// * @ClassName: pojo
// * @Author: zhou_yangtao@yeah.net
// * @Description: 用户授权访问页面
// * @Date: 21:29 2021/3/16
// * @Version: 1.0
// */
//public class SelfUserEntity   implements Serializable, UserDetails {
//    /**
//     * 用户ID
//     */
//    private Long userId;
//    /**
//     * 用户名
//     */
//    private String username;
//    /**
//     * 密码
//     */
//    private String password;
//    /**
//     * 状态:NORMAL正常  PROHIBIT禁用
//     */
//    private String status;
//
//
//    /**
//     * 用户角色
//     */
//    private Collection<GrantedAuthority> authorities;
//    /**
//     * 账户是否过期
//     */
//    private boolean isAccountNonExpired = false;
//    /**
//     * 账户是否被锁定
//     */
//    private boolean isAccountNonLocked = false;
//    /**
//     * 证书是否过期
//     */
//    private boolean isCredentialsNonExpired = false;
//    /**
//     * 账户是否有效
//     */
//    private boolean isEnabled = true;
//
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Override
//    public Collection<GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Collection<GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return isAccountNonExpired;
//    }
//
//    public void setAccountNonExpired(boolean accountNonExpired) {
//        isAccountNonExpired = accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return isAccountNonLocked;
//    }
//
//    public void setAccountNonLocked(boolean accountNonLocked) {
//        isAccountNonLocked = accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isCredentialsNonExpired;
//    }
//
//    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
//        isCredentialsNonExpired = credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        isEnabled = enabled;
//    }
//
//
//    @Override
//    public String toString() {
//        return "SelfUserEntity{" +
//                "userId=" + userId +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", status='" + status + '\'' +
//                ", authorities=" + authorities +
//                ", isAccountNonExpired=" + isAccountNonExpired +
//                ", isAccountNonLocked=" + isAccountNonLocked +
//                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
//                ", isEnabled=" + isEnabled +
//                '}';
//    }
//}

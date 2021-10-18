//package com.zyt.entity.vo;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
///**
// * @ProjectName: online_drink_mall
// * @Package: com.zyt.entity.vo
// * @ClassName: UserLoginVo
// * @Author: zhou_yangtao@yeah.net
// * @Description: 用户登录验证信息
// * @Date: 15:38 2021/3/18
// * @Version: 1.0
// */
//public class UserLoginVo {
//
//    /**
//     * 用户ID
//     */
//    private Long userId;
//
//    /**
//     * 用户名
//     */
//    private String username;
//
//    /**
//     * 密码
//     */
//    private String password;
//
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
//
//
//
//    public UserLoginVo() {
//        super();
//    }
//
//    public UserLoginVo(Long userId, String username, String password, String status, Collection<GrantedAuthority> authorities) {
//        this.userId = userId;
//        this.username = username;
//        this.password = password;
//        this.status = status;
//        this.authorities = authorities;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
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
//    public Collection<GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Collection<GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    @Override
//    public String toString() {
//        return "UserLoginVo{" +
//                "userId=" + userId +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", status='" + status + '\'' +
//                ", authorities=" + authorities +
//                '}';
//    }
//}

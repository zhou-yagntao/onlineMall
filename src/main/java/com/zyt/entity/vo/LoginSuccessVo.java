package com.zyt.entity.vo;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: LoginSuccessVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户登录成功vo
 * @Date: 21:34 2021/4/2
 * @Version: 1.0
 */
public class LoginSuccessVo {
    private Integer user_id;

    private String realname;

    private String username;

    private String phone;

    private String user_passWord;

    private String user_status;

    private Integer role_id;

    private List<String> role;

    public LoginSuccessVo(){}

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_passWord() {
        return user_passWord;
    }

    public void setUser_passWord(String user_passWord) {
        this.user_passWord = user_passWord;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginSuccessVo{" +
                "user_id=" + user_id +
                ", realname='" + realname + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", user_passWord='" + user_passWord + '\'' +
                ", user_status='" + user_status + '\'' +
                ", role_id=" + role_id +
                ", role=" + role +
                '}';
    }
}

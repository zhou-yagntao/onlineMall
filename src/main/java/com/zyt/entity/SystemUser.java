package com.zyt.entity;

import java.io.Serializable;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: BackUser
 * @Author: zhou_yangtao@yeah.net
 * @Description: 后台管理系统相关用户
 * @Date: 20:45 2021/3/15
 * @Version: 1.0
 */
public class SystemUser implements Serializable {

    private Integer user_id;

    private String realname;

    private String username;

    private String phone;

    private String user_passWord;

    private String user_status;

    private Integer role_id;

    private SystemRole systemRole;


    public SystemUser() {
        super();
    }


    public SystemUser(Integer user_id, String realname, String username, String phone, String user_passWord, String user_status) {
        this.user_id = user_id;
        this.realname = realname;
        this.username = username;
        this.phone = phone;
        this.user_passWord = user_passWord;
        this.user_status = user_status;
    }

    public SystemUser(Integer user_id, String realname, String username, String phone, String user_passWord, String user_status, Integer role_id) {
        this.user_id = user_id;
        this.realname = realname;
        this.username = username;
        this.phone = phone;
        this.user_passWord = user_passWord;
        this.user_status = user_status;
        this.role_id = role_id;
    }

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

    public SystemRole getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }

    @Override
    public String toString() {
        return "BackUser{" +
                "user_id=" + user_id +
                ", realname='" + realname + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", user_passWord='" + user_passWord + '\'' +
                ", user_status='" + user_status + '\'' +
                ", role_id=" + role_id +
                ", systemRole=" + systemRole +
                '}';
    }
}

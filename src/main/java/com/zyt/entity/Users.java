package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: Users
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户表
 * @Date: 10:38 2021/2/9
 * @Version: 1.0
 */
public class Users {
    private Integer UserId;
    private String UserName;
    private String UserNickName;
    private String LoginPassword;
    private String UserIdentity;
    private String UserPhoneNum;

    public Users(){super();}

    public Users(Integer userId, String userName, String userNickName, String loginPassword, String userIdentity, String userPhoneNum) {
        UserId = userId;
        UserName = userName;
        this.UserNickName = userNickName;
        LoginPassword = loginPassword;
        UserIdentity = userIdentity;
        UserPhoneNum = userPhoneNum;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserNickName() {
        return UserNickName;
    }

    public void setUserNickName(String userNickName) {
        this.UserNickName = userNickName;
    }

    public String getLoginPassword() {
        return LoginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        LoginPassword = loginPassword;
    }

    public String getUserIdentity() {
        return UserIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        UserIdentity = userIdentity;
    }

    public String getUserPhoneNum() {
        return UserPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        UserPhoneNum = userPhoneNum;
    }

    @Override
    public String toString() {
        return "Users{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", userNickName='" + UserNickName + '\'' +
                ", LoginPassword='" + LoginPassword + '\'' +
                ", UserIdentity='" + UserIdentity + '\'' +
                ", UserPhoneNum='" + UserPhoneNum + '\'' +
                '}';
    }
}

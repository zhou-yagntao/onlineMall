package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: EncapsulateUserInfoTo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 封装购物车用户是否登录结果
 * @Date: 19:09 2021/2/22
 * @Version: 1.0
 */
public class EncapsulateUserInfoTo {

    private String userId;

    private String userKey;

    private boolean tempUser = false;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public boolean isTempUser() {
        return tempUser;
    }

    public void setTempUser(boolean tempUser) {
        this.tempUser = tempUser;
    }

    @Override
    public String toString() {
        return "EncapsulateUserInfoTo{" +
                "userId='" + userId + '\'' +
                ", userKey='" + userKey + '\'' +
                ", tempUser=" + tempUser +
                '}';
    }
}

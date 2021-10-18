package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: SocailUser
 * @Author: zhou_yangtao@yeah.net
 * @Description: 第三方返回结果实体信息类
 * @Date: 11:53 2021/2/21
 * @Version: 1.0
 */
public class SocailUser {

    private String access_token;

    private String remind_in;

    private long expires_in;

    private String uid;

    private String isRealName;

    public SocailUser() {
        super();
    }

    public SocailUser(String access_token, String remind_in, long expires_in, String uid, String isRealName) {
        this.access_token = access_token;
        this.remind_in = remind_in;
        this.expires_in = expires_in;
        this.uid = uid;
        this.isRealName = isRealName;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRemind_in() {
        return remind_in;
    }

    public void setRemind_in(String remind_in) {
        this.remind_in = remind_in;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
    }

    @Override
    public String toString() {
        return "SocailUser{" +
                "access_token='" + access_token + '\'' +
                ", remind_in='" + remind_in + '\'' +
                ", expires_in=" + expires_in +
                ", uid='" + uid + '\'' +
                ", isRealName='" + isRealName + '\'' +
                '}';
    }
}

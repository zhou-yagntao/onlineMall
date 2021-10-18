package com.zyt.entity;

import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: VipMember
 * @Author: zhou_yangtao@yeah.net
 * @Description: 会员信息表
 * @Date: 13:46 2021/2/21
 * @Version: 1.0
 */
public class VipMember {
    private Integer vid;
    private Integer levelId;
    private String username;
    private String nickname;
    private String mobile;
    private String email;
    private String header;
    private String gender;
    private Date birth;
    private String city;
    private String signs;
    private String userType;
    private Integer integration;
    private Integer growth;
    private Boolean statuss;
    private Date create_time;
    private String socialUserId;
    private String accessToken;
    private String expiressIn;
    private Integer integral;


    public  VipMember(){
        super();
    }

    public VipMember(Integer vid, Integer levelId, String username, String nickname, String mobile, String email, String header, String gender, Date birth, String city, String signs, String userType, Integer integration, Integer growth, Boolean statuss, Date create_time) {
        this.vid = vid;
        this.levelId = levelId;
        this.username = username;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.header = header;
        this.gender = gender;
        this.birth = birth;
        this.city = city;
        this.signs = signs;
        this.userType = userType;
        this.integration = integration;
        this.growth = growth;
        this.statuss = statuss;
        this.create_time = create_time;
    }

    public VipMember(Integer vid, Integer levelId, String username, String nickname, String mobile, String email, String header, String gender, Date birth, String city, String signs, String userType, Integer integration, Integer growth, Boolean statuss, Date create_time, String userId) {
        this.vid = vid;
        this.levelId = levelId;
        this.username = username;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.header = header;
        this.gender = gender;
        this.birth = birth;
        this.city = city;
        this.signs = signs;
        this.userType = userType;
        this.integration = integration;
        this.growth = growth;
        this.statuss = statuss;
        this.create_time = create_time;
        this.socialUserId = userId;
    }

    public VipMember(Integer vid, Integer levelId, String username, String nickname, String mobile, String email, String header, String gender, Date birth, String city, String signs, String userType, Integer integration, Integer growth, Boolean statuss, Date create_time, String socialUserId, String accessToken, String expiressIn) {
        this.vid = vid;
        this.levelId = levelId;
        this.username = username;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.header = header;
        this.gender = gender;
        this.birth = birth;
        this.city = city;
        this.signs = signs;
        this.userType = userType;
        this.integration = integration;
        this.growth = growth;
        this.statuss = statuss;
        this.create_time = create_time;
        this.socialUserId = socialUserId;
        this.accessToken = accessToken;
        this.expiressIn = expiressIn;
    }

    public VipMember(Integer vid, Integer levelId, String username, String nickname, String mobile, String email, String header, String gender, Date birth, String city, String signs, String userType, Integer integration, Integer growth, Boolean statuss, Date create_time, String socialUserId, String accessToken, String expiressIn, Integer integral) {
        this.vid = vid;
        this.levelId = levelId;
        this.username = username;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.header = header;
        this.gender = gender;
        this.birth = birth;
        this.city = city;
        this.signs = signs;
        this.userType = userType;
        this.integration = integration;
        this.growth = growth;
        this.statuss = statuss;
        this.create_time = create_time;
        this.socialUserId = socialUserId;
        this.accessToken = accessToken;
        this.expiressIn = expiressIn;
        this.integral = integral;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSigns() {
        return signs;
    }

    public void setSigns(String signs) {
        this.signs = signs;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Boolean getStatuss() {
        return statuss;
    }

    public void setStatuss(Boolean statuss) {
        this.statuss = statuss;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getSocialUserId() {
        return socialUserId;
    }

    public void setSocialUserId(String socialUserId) {
        this.socialUserId = socialUserId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiressIn() {
        return expiressIn;
    }

    public void setExpiressIn(String expiressIn) {
        this.expiressIn = expiressIn;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "VipMember{" +
                "vid=" + vid +
                ", levelId=" + levelId +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", header='" + header + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                ", city='" + city + '\'' +
                ", signs='" + signs + '\'' +
                ", userType='" + userType + '\'' +
                ", integration=" + integration +
                ", growth=" + growth +
                ", statuss=" + statuss +
                ", create_time=" + create_time +
                ", socialUserId='" + socialUserId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", expiressIn='" + expiressIn + '\'' +
                ", integral=" + integral +
                '}';
    }
}

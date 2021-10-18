package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: UserSimilarity
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户相似度实体类
 * @Date: 16:37 2021/2/9
 * @Version: 1.0
 */
public class UserSimilarity {

    private Integer userSid;
    private Long userId;
    private Long userRefId;
    private Double similarity;

    public  UserSimilarity(){super();}

    public UserSimilarity(Integer userSid, Long userId, Long userRefId, Double similarity) {
        this.userSid = userSid;
        this.userId = userId;
        this.userRefId = userRefId;
        this.similarity = similarity;
    }

    public Integer getUserSid() {
        return userSid;
    }

    public void setUserSid(Integer userSid) {
        this.userSid = userSid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserRefId() {
        return userRefId;
    }

    public void setUserRefId(Long userRefId) {
        this.userRefId = userRefId;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    @Override
    public String toString() {
        return "UserSimilarity{" +
                "userSid=" + userSid +
                ", userId=" + userId +
                ", userRefId=" + userRefId +
                ", similarity=" + similarity +
                '}';
    }
}

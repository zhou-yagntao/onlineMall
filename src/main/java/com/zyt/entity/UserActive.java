package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: UserActive
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 16:23 2021/2/9
 * @Version: 1.0
 */
public class UserActive {

    private Integer ActiveId;
    private Long UserId;
    private Long  ProductId;
    private Long hits;

    public UserActive(){super();}

    public UserActive(Integer activeId, Long userId, Long productId, Long hits) {
        ActiveId = activeId;
        UserId = userId;
        ProductId = productId;
        this.hits = hits;
    }

    public Integer getActiveId() {
        return ActiveId;
    }

    public void setActiveId(Integer activeId) {
        ActiveId = activeId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public Long getHits() {
        return hits;
    }

    public void setHits(Long hits) {
        this.hits = hits;
    }
}

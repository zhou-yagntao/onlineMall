package com.zyt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: ProductClick
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品点击量表
 * @Date: 12:35 2021/1/25
 * @Version: 1.0
 */
public class ProductClick implements Serializable {
    private Integer proClickId;
    private Integer user_id;
    private Integer  proClickProId;
    private Integer  proClickCounts;
    private Date proClickTime;

    public ProductClick(){
    }

    public ProductClick(Integer proClickId, Integer user_id, Integer proClickProId, Integer proClickCounts, Date proClickTime) {
        this.proClickId = proClickId;
        this.user_id = user_id;
        this.proClickProId = proClickProId;
        this.proClickCounts = proClickCounts;
        this.proClickTime = proClickTime;
    }

    public Integer getProClickId() {
        return proClickId;
    }

    public void setProClickId(Integer proClickId) {
        this.proClickId = proClickId;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProClickProId() {
        return proClickProId;
    }

    public void setProClickProId(Integer proClickProId) {
        this.proClickProId = proClickProId;
    }

    public Integer getProClickCounts() {
        return proClickCounts;
    }

    public void setProClickCounts(Integer proClickCounts) {
        this.proClickCounts = proClickCounts;
    }

    public Date getProClickTime() {
        return proClickTime;
    }

    public void setProClickTime(Date proClickTime) {
        this.proClickTime = proClickTime;
    }
}

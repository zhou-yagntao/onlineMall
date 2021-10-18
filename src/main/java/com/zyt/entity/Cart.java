package com.zyt.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: Cart
 * @Author: zhou_yangtao@yeah.net
 * @Description: 购物车
 * @Date: 20:01 2021/2/20
 * @Version: 1.0
 */
public class Cart {

    private Integer countNum;//商品数量

    private Integer countType;//商品类型

    private Integer totalAmount;//商品总价

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getCountType() {
        return countType;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}

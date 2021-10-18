package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: DifferentStatusPriceTo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 不同商品属性价格
 * @Date: 14:43 2021/2/24
 * @Version: 1.0
 */
public class DifferentStatusPriceTo {

    private Integer smallPrice;

    private Integer midPrice;

    private  Integer bigPrice;

    public Integer getSmallPrice() {
        return smallPrice;
    }

    public void setSmallPrice(Integer smallPrice) {
        this.smallPrice = smallPrice;
    }

    public Integer getMidPrice() {
        return midPrice;
    }

    public void setMidPrice(Integer midPrice) {
        this.midPrice = midPrice;
    }

    public Integer getBigPrice() {
        return bigPrice;
    }

    public void setBigPrice(Integer bigPrice) {
        this.bigPrice = bigPrice;
    }
}

package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: ProductSaleInfo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品销售数量信息
 * @Date: 10:40 2021/2/24
 * @Version: 1.0
 */
public class ProductSaleInfo {

    private Integer prodsId;

    private Integer pproId;

    private Integer saleCount;

    public ProductSaleInfo(){super();}

    public ProductSaleInfo(Integer prodsId, Integer pproId, Integer saleCount) {
        this.prodsId = prodsId;
        this.pproId = pproId;
        this.saleCount = saleCount;
    }

    public Integer getProdsId() {
        return prodsId;
    }

    public void setProdsId(Integer prodsId) {
        this.prodsId = prodsId;
    }

    public Integer getPproId() {
        return pproId;
    }

    public void setPproId(Integer pproId) {
        this.pproId = pproId;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    @Override
    public String toString() {
        return "ProductSaleInfo{" +
                "prodsId=" + prodsId +
                ", pproId=" + pproId +
                ", saleCount=" + saleCount +
                '}';
    }
}

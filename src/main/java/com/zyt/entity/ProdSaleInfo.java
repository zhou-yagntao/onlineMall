package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: ProdSaleInfo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品信息管理实体类
 * @Date: 11:59 2021/2/16
 * @Version: 1.0
 */
public class ProdSaleInfo {
    private Integer prodsId;
    private Integer pproId;
    private  Integer saleCount;

    public ProdSaleInfo(){
       super();
    }

    public ProdSaleInfo(Integer prodsId, Integer pproId, Integer saleCount) {
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
        return "ProdSaleInfo{" +
                "prodsId=" + prodsId +
                ", pproId=" + pproId +
                ", saleCount=" + saleCount +
                '}';
    }
}

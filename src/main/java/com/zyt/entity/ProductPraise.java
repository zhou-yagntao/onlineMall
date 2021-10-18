package com.zyt.entity;

import java.io.Serializable;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: ProductPraise
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品好评表实体类
 * @Date: 12:37 2021/1/25
 * @Version: 1.0
 */
public class ProductPraise implements Serializable {
    private Integer ProdPid;
    private Integer ProdPProId;
    private String ProdPdesc;
    private Double ProdScore;
    private String ProdPTime;

    public ProductPraise() {
        super();
    }
    public ProductPraise(Integer prodPid) {
        ProdPid = prodPid;
    }

    public ProductPraise(Integer prodPid, Integer prodPProId, String prodPdesc, Double prodScore, String prodPTime) {
        ProdPid = prodPid;
        ProdPProId = prodPProId;
        ProdPdesc = prodPdesc;
        ProdScore = prodScore;
        ProdPTime = prodPTime;
    }

    public Integer getProdPid() {
        return ProdPid;
    }

    public void setProdPid(Integer prodPid) {
        ProdPid = prodPid;
    }

    public Integer getProdPProId() {
        return ProdPProId;
    }

    public void setProdPProId(Integer prodPProId) {
        ProdPProId = prodPProId;
    }

    public String getProdPdesc() {
        return ProdPdesc;
    }

    public void setProdPdesc(String prodPdesc) {
        ProdPdesc = prodPdesc;
    }

    public Double getProdScore() {
        return ProdScore;
    }

    public void setProdScore(Double prodScore) {
        ProdScore = prodScore;
    }

    public String getProdPTime() {
        return ProdPTime;
    }

    public void setProdPTime(String prodPTime) {
        ProdPTime = prodPTime;
    }
}

package com.zyt.entity.to.es;

import java.math.BigDecimal;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: ProUpEslasticsearch
 * @Author: zhou_yangtao@yeah.net
 * @Description: es搜索服务保存的商品信息
 * @Date: 11:46 2021/2/4
 * @Version: 1.0
 */
public class ProUpEslasticsearch {

    private Long prodId;

    private Long prodBrandId;

    private String prodName;

    private BigDecimal prodPrice;

    private String prodImg;

    private Long hotScore;

    private String brandName;

    private String storeName;

    public ProUpEslasticsearch() {
        super();
    }

    public ProUpEslasticsearch(Long prodId) {
        this.prodId = prodId;
    }

    public ProUpEslasticsearch(Long prodId, Long prodBrandId, String prodName, BigDecimal prodPrice, String prodImg, Long hotScore, String brandName, String storeName) {
        this.prodId = prodId;
        this.prodBrandId = prodBrandId;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodImg = prodImg;
        this.hotScore = hotScore;
        this.brandName = brandName;
        this.storeName = storeName;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getProdBrandId() {
        return prodBrandId;
    }

    public void setProdBrandId(Long prodBrandId) {
        this.prodBrandId = prodBrandId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public BigDecimal getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(BigDecimal prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    public Long getHotScore() {
        return hotScore;
    }

    public void setHotScore(Long hotScore) {
        this.hotScore = hotScore;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}

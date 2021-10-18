package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: ProductDetailsTo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 封装详情业信息数据
 * @Date: 12:41 2021/2/23
 * @Version: 1.0
 */
public class ProductDetailsTo {

    private Integer productId;

    private String productName;

    private String productImg;

    private String BrandName;

    private String storeName;

    private String prodMoral;

    private String prodNvipPrice;

    private String prodVipPrice;

    private Integer saleCounts;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProdMoral() {
        return prodMoral;
    }

    public void setProdMoral(String prodMoral) {
        this.prodMoral = prodMoral;
    }

    public String getProdNvipPrice() {
        return prodNvipPrice;
    }

    public void setProdNvipPrice(String prodNvipPrice) {
        this.prodNvipPrice = prodNvipPrice;
    }

    public String getProdVipPrice() {
        return prodVipPrice;
    }

    public void setProdVipPrice(String prodVipPrice) {
        this.prodVipPrice = prodVipPrice;
    }

    public Integer getSaleCounts() {
        return saleCounts;
    }

    public void setSaleCounts(Integer saleCounts) {
        this.saleCounts = saleCounts;
    }

    @Override
    public String toString() {
        return "ProductDetailsTo{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productImg='" + productImg + '\'' +
                ", BrandName='" + BrandName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", prodMoral='" + prodMoral + '\'' +
                ", prodNvipPrice='" + prodNvipPrice + '\'' +
                ", prodVipPrice='" + prodVipPrice + '\'' +
                ", saleCounts=" + saleCounts +
                '}';
    }
}

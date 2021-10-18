package com.zyt.entity;

import java.math.BigDecimal;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: CartItem
 * @Author: zhou_yangtao@yeah.net
 * @Description: 购物项内容
 * @Date: 20:01 2021/2/20
 * @Version: 1.0
 */
public class CartItem {

    private Integer cartItemId;
    private Integer userId;
    private Integer prodId;
    private Boolean isCheck = true;
    private String prodName;
    private String prodImg;
    private String prodStatus;
    private Integer prodPrice;
    private Integer count;
    private Integer totalPrice;
    private String storeName;


    public CartItem() {
     super();
    }

    public CartItem(Integer cartItemId, Integer userId, Integer prodId, Boolean isCheck, String prodName, String prodImg, String prodStatus, Integer prodPrice, Integer count, Integer totalPrice, String storeName) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.prodId = prodId;
        this.isCheck = isCheck;
        this.prodName = prodName;
        this.prodImg = prodImg;
        this.prodStatus = prodStatus;
        this.prodPrice = prodPrice;
        this.count = count;
        this.totalPrice = totalPrice;
        this.storeName = storeName;
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", userId=" + userId +
                ", prodId=" + prodId +
                ", isCheck=" + isCheck +
                ", prodName='" + prodName + '\'' +
                ", prodImg='" + prodImg + '\'' +
                ", prodStatus='" + prodStatus + '\'' +
                ", prodPrice=" + prodPrice +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}

package com.zyt.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: Store
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺信息实体类
 * @Date: 17:47 2021/1/18
 * @Version: 1.0
 */

public class Store implements Serializable {
    private Integer StoreId;
    private String StoreName;
    private String StoreOwner;
    private String StoreImg;
    private String StoreAddress;
    private Double Longitude;
    private Double Atitude;
    private Date SettledDate;
    private String StoreDesc;

    public Store(){}

    public Store(String storeName, String storeOwner, String storeImg, String storeAddress, Double longitude, Double atitude, Date settledDate, String storeDesc) {
        StoreName = storeName;
        StoreOwner = storeOwner;
        StoreImg = storeImg;
        StoreAddress = storeAddress;
        Longitude = longitude;
        Atitude = atitude;
        SettledDate = settledDate;
        StoreDesc = storeDesc;
    }

    public Store(Integer storeId, String storeName, String storeOwner, String storeImg, String storeAddress, Double longitude, Double atitude, Date settledDate, String storeDesc) {
        StoreId = storeId;
        StoreName = storeName;
        StoreOwner = storeOwner;
        StoreImg = storeImg;
        StoreAddress = storeAddress;
        Longitude = longitude;
        Atitude = atitude;
        SettledDate = settledDate;
        StoreDesc = storeDesc;
    }

    public Integer getStoreId() {
        return StoreId;
    }

    public void setStoreId(Integer storeId) {
        StoreId = storeId;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getStoreOwner() {
        return StoreOwner;
    }

    public void setStoreOwner(String storeOwner) {
        StoreOwner = storeOwner;
    }

    public String getStoreImg() {
        return StoreImg;
    }

    public void setStoreImg(String storeImg) {
        StoreImg = storeImg;
    }

    public String getStoreAddress() {
        return StoreAddress;
    }

    public void setStoreAddress(String storeAddress) {
        StoreAddress = storeAddress;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getAtitude() {
        return Atitude;
    }

    public void setAtitude(Double atitude) {
        Atitude = atitude;
    }

    public Date getSettledDate() {
        return SettledDate;
    }

    public void setSettledDate(Date settledDate) {
        SettledDate = settledDate;
    }

    public String getStoreDesc() {
        return StoreDesc;
    }

    public void setStoreDesc(String storeDesc) {
        StoreDesc = storeDesc;
    }

    @Override
    public String toString() {
        return "Store{" +
                "StoreId=" + StoreId +
                ", StoreName='" + StoreName + '\'' +
                ", StoreOwner='" + StoreOwner + '\'' +
                ", StoreImg='" + StoreImg + '\'' +
                ", StoreAddress='" + StoreAddress + '\'' +
                ", Longitude=" + Longitude +
                ", Atitude=" + Atitude +
                ", SettledDate=" + SettledDate +
                ", StoreDesc='" + StoreDesc + '\'' +
                '}';
    }
}

package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: TopFiveStoreInfoTo
 * @Author: zhou_yangtao@yeah.net
 * @Description: top5店铺信息封装返回结果
 * @Date: 11:51 2021/2/24
 * @Version: 1.0
 */
public class TopFiveStoreInfoTo {

    private Integer storeId;

    private String storeName;

    private String storeImg;

    private Integer storeHtis;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public Integer getStoreHtis() {
        return storeHtis;
    }

    public void setStoreHtis(Integer storeHtis) {
        this.storeHtis = storeHtis;
    }


    @Override
    public String toString() {
        return "TopFiveStoreInfoTo{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", storeImg='" + storeImg + '\'' +
                ", storeHtis=" + storeHtis +
                '}';
    }
}

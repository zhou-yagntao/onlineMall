package com.zyt.entity;

import java.io.Serializable;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: StoreHits
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺浏览量实体类
 * @Date: 10:36 2021/2/10
 * @Version: 1.0
 */
public class StoreHits  implements Serializable {

    private Integer sClickId;
    private Integer sstoreId;
    private Integer sotreHits;

    private Store store;

    public StoreHits(){super();}

    public StoreHits(Integer sClickId, Integer sstoreId, Integer sotreHits) {
        this.sClickId = sClickId;
        this.sstoreId = sstoreId;
        this.sotreHits = sotreHits;
    }

    public StoreHits(Integer sClickId, Integer sstoreId, Integer sotreHits, Store store) {
        this.sClickId = sClickId;
        this.sstoreId = sstoreId;
        this.sotreHits = sotreHits;
        this.store = store;
    }

    public Integer getsClickId() {
        return sClickId;
    }

    public void setsClickId(Integer sClickId) {
        this.sClickId = sClickId;
    }

    public Integer getSstoreId() {
        return sstoreId;
    }

    public void setSstoreId(Integer sstoreId) {
        this.sstoreId = sstoreId;
    }

    public Integer getSotreHits() {
        return sotreHits;
    }

    public void setSotreHits(Integer sotreHits) {
        this.sotreHits = sotreHits;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "StoreHits{" +
                "sClickId=" + sClickId +
                ", sstoreId=" + sstoreId +
                ", sotreHits=" + sotreHits +
                ", store=" + store +
                '}';
    }
}

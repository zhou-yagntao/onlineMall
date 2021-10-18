package com.zyt.entity;

import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: StoreProfit
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺收益表
 * @Date: 15:53 2021/3/4
 * @Version: 1.0
 */
public class StoreProfit {
    private Integer profit_id;
    private Integer store_Id;
    private Double profit;
    private Date getProfitDate;

    public Integer getProfit_id() {
        return profit_id;
    }

    public void setProfit_id(Integer profit_id) {
        this.profit_id = profit_id;
    }

    public Integer getStore_Id() {
        return store_Id;
    }

    public void setStore_Id(Integer store_Id) {
        this.store_Id = store_Id;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Date getGetProfitDate() {
        return getProfitDate;
    }

    public void setGetProfitDate(Date getProfitDate) {
        this.getProfitDate = getProfitDate;
    }

    @Override
    public String toString() {
        return "StoreProfit{" +
                "profit_id=" + profit_id +
                ", store_Id=" + store_Id +
                ", profit=" + profit +
                ", getProfitDate=" + getProfitDate +
                '}';
    }
}

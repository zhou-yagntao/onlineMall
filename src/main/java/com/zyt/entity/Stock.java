package com.zyt.entity;

import jdk.internal.org.objectweb.asm.tree.IincInsnNode;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: Stock
 * @Author: zhou_yangtao@yeah.net
 * @Description: 库存表
 * @Date: 18:31 2021/2/27
 * @Version: 1.0
 */
public class Stock {
    private Integer sid;
    private Integer sproId;
    private Integer stockCounts;
    private Integer stock_lock;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSproId() {
        return sproId;
    }

    public void setSproId(Integer sproId) {
        this.sproId = sproId;
    }

    public Integer getStockCounts() {
        return stockCounts;
    }

    public void setStockCounts(Integer stockCounts) {
        this.stockCounts = stockCounts;
    }

    public Integer getStock_lock() {
        return stock_lock;
    }

    public void setStock_lock(Integer stock_lock) {
        this.stock_lock = stock_lock;
    }
}

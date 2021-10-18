package com.zyt.entity.to.mq;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.to.mq
 * @ClassName: StockLock
 * @Author: zhou_yangtao@yeah.net
 * @Description: 库存锁定成功
 * @Date: 13:31 2021/3/3
 * @Version: 1.0
 */
public class StockLockTo {

    private Integer id; //库存工作单

    private OrderStockDetailsLockTo orderStockDetailsLockTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderStockDetailsLockTo getOrderStockDetailsLockTo() {
        return orderStockDetailsLockTo;
    }

    public void setOrderStockDetailsLockTo(OrderStockDetailsLockTo orderStockDetailsLockTo) {
        this.orderStockDetailsLockTo = orderStockDetailsLockTo;
    }
}
